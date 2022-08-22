package com.jikaigg.hotal.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jikaigg.hotal.mapper.HotelMapper;
import com.jikaigg.hotal.pojo.Hotel;
import com.jikaigg.hotal.pojo.HotelDoc;
import com.jikaigg.hotal.pojo.PageResult;
import com.jikaigg.hotal.pojo.RequestParam;
import com.jikaigg.hotal.service.IHotelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

    @Autowired
    private RestHighLevelClient client;


    @Override
    public PageResult search(RequestParam requestParam) {
        try {
            // 准备request
            SearchRequest request = new SearchRequest("hotal");

            /*// 准备dsl
            // 关键字搜索
            String key = requestParam.getKey();
            if (StringUtils.isNotBlank(key))
                request.source().query(QueryBuilders.matchQuery("all", key));
            else
                request.source().query(QueryBuilders.matchAllQuery());

            // 分页
            Integer page = requestParam.getPage();
            Integer size = requestParam.getSize();
            request.source().from((page - 1) * size).size(size);*/

            // 准备dsl
            BoolQueryBuilder boolQuery = buildBasicQuery(requestParam);

            request.source().query(boolQuery);

            // 分页
            Integer page = requestParam.getPage();
            Integer size = requestParam.getSize();
            request.source().from((page - 1) * size).size(size);

            // 地理坐标离我最近排序
            if (StringUtils.isNotBlank(requestParam.getLocation()))
                request.source().sort(
                        SortBuilders.geoDistanceSort("location", new GeoPoint(requestParam.getLocation()))
                                .order(SortOrder.ASC).unit(DistanceUnit.KILOMETERS));

            // 发送请求，得到相应
            SearchResponse result = client.search(request, RequestOptions.DEFAULT);

            // 解析结果,返回
            return dealResult(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, List<String>> filters(RequestParam requestParam) throws IOException {
        SearchRequest request = new SearchRequest("hotal");

        // 准备DSL
        buildBasicQuery(requestParam);
        BuildAggregation(request);

        // 发出请求
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = search.getAggregations();
        Map<String, List<String>> result = new HashMap<>();
        List<String> brandAgg = getAggByName(aggregations, "brandAgg");
        result.put("brand", brandAgg);
        List<String> cityAgg = getAggByName(aggregations, "cityAgg");
        result.put("city", cityAgg);
        List<String> starNameAgg = getAggByName(aggregations, "starNameAgg");
        result.put("starName", starNameAgg);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        try {
            log.info("监听到消息队列中有删除请求，id为：{}", id.toString());
            // 准备request
            DeleteRequest deleteRequest = new DeleteRequest("hotal", id.toString());
            // 发送请求
            client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertById(Long id) {
        try {
            log.info("监听到消息队列中有新增或修改请求，id为：{}", id.toString());
            // 根据id查询数据库
            Hotel hotel = getById(id);
            HotelDoc hotelDoc = new HotelDoc(hotel);
            // 准备request
            IndexRequest request = new IndexRequest("hotal").id(hotel.getId().toString());
            // 准备DSL
            request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
            // 发送请求
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getAggByName(Aggregations aggregations, String agg) {
        List<String> brandList = new ArrayList<>();
//        System.out.println(search);

        Terms aggs = aggregations.get(agg);
        List<? extends Terms.Bucket> buckets = aggs.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String keyAsString = bucket.getKeyAsString();
            brandList.add(keyAsString);
        }
        return brandList;
    }

    private void BuildAggregation(SearchRequest request) {
        request.source()
                .aggregation(AggregationBuilders
                        .terms("brandAgg")
                        .field("brand")
                        .size(100));

        request.source()
                .aggregation(AggregationBuilders
                        .terms("cityAgg")
                        .field("city")
                        .size(100));

        request.source()
                .aggregation(AggregationBuilders
                        .terms("starNameAgg")
                        .field("starName")
                        .size(100));
    }

    private BoolQueryBuilder buildBasicQuery(RequestParam requestParam) {
        // 构建BooleanQuery
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 关键字搜索
        String key = requestParam.getKey();
        if (StringUtils.isNotBlank(key))
            boolQuery.must(QueryBuilders.matchQuery("all", key));
        else
            boolQuery.must(QueryBuilders.matchAllQuery());

        // 城市条件
        if (StringUtils.isNotBlank(requestParam.getCity()))
            boolQuery.filter(QueryBuilders.termQuery("city", requestParam.getCity()));
        if (StringUtils.isNotBlank(requestParam.getStarName()))
            boolQuery.filter(QueryBuilders.termQuery("starName", requestParam.getStarName()));
        if (StringUtils.isNotBlank(requestParam.getBrand()))
            boolQuery.filter(QueryBuilders.termQuery("brand", requestParam.getBrand()));
        if (StringUtils.isNotBlank(requestParam.getMinPrice()) && StringUtils.isNotBlank(requestParam.getMaxPrice()))
            boolQuery.filter(QueryBuilders.rangeQuery("price").lte(requestParam.getMaxPrice()).gte(requestParam.getMinPrice()));
        return boolQuery;
    }

    private PageResult dealResult(SearchResponse search) {
        PageResult pageResult = new PageResult();
        // 获取hits
        SearchHits hits = search.getHits();
        // 获取总条数
        long value = hits.getTotalHits().value;
        pageResult.setTotal(value);
        SearchHit[] hits1 = hits.getHits();
        List<HotelDoc> hotelDocs = new ArrayList<>();
        for (SearchHit hit : hits1) {
            String sourceAsString = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(sourceAsString, HotelDoc.class);
            hotelDocs.add(hotelDoc);
            Object[] sortValues = hit.getSortValues();
            if (sortValues != null && sortValues.length > 0)
                hotelDoc.setDistance(sortValues[0].toString());
        }
        pageResult.setHotels(hotelDocs);
        return pageResult;
    }
}