package com.jikaigg.hotal;

import com.alibaba.fastjson.JSON;
import com.jikaigg.hotal.pojo.Hotel;
import com.jikaigg.hotal.service.IHotelService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

public class HotalSearchTest {
    @Autowired
    private IHotelService iHotelService;
    private RestHighLevelClient client;

    @Before
    public void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.1.3:9200")
        ));
    }

    @After
    public void tearDown() throws IOException {
        this.client.close();
    }

    @Test
    public void test1() throws IOException {
        SearchRequest request = new SearchRequest("hotal");

        // 查询所有
        request.source().query(QueryBuilders.matchAllQuery());

        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        dealResult(search);
    }

    /*
    * 条件查询
    * */

    @Test
    public void test2() throws IOException {
        SearchRequest request = new SearchRequest("hotal");

        // 条件查询
        request.source().query(QueryBuilders.matchQuery("all","如家"));

        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        dealResult(search);
    }

    /*
    * 布尔查询
    * */
    @Test
    public void test3() throws IOException {
        SearchRequest request = new SearchRequest("hotal");

        // 创建布尔查询
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.termQuery("city","上海"));
        query.filter(QueryBuilders.rangeQuery("price").lte(250));
        // 条件查询
        request.source().query(query);

        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        dealResult(search);
    }

    @Test
    public void test4() throws IOException {
        SearchRequest request = new SearchRequest("hotal");

        // 条件查询，from从哪条开始查，size查询多少条，sort排序,SortOrder.DESC降序排序
        request.source()
                .query(QueryBuilders.matchQuery("city","上海"))
                .sort("price", SortOrder.DESC)
                .from(10)
                .size(10);

        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        dealResult(search);
    }

    /*
    * 高亮
    * */
    @Test
    public void test5() throws IOException {
        SearchRequest request = new SearchRequest("hotal");

        // 条件查询，from从哪条开始查，size查询多少条，sort排序,SortOrder.DESC降序排序
        request.source()
                .query(QueryBuilders.matchQuery("name","如家"))
                .highlighter(
                        new HighlightBuilder()
                                .field("name")
                                .requireFieldMatch(false)
                );

        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        dealResult(search);
    }


    /*
     * 聚合
     * */
    @Test
    public void test6() throws IOException {
        SearchRequest request = new SearchRequest("hotal");

        // 准备DSL
        request.source().size(0);
        request.source()
                .aggregation(AggregationBuilders
                        .terms("brandAgg")
                        .field("brand")
                        .size(10));

        // 发出请求
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
//        System.out.println(search);
        dealAggResult(search);
    }

    private void dealAggResult(SearchResponse search) {
        Aggregations aggregations = search.getAggregations();
        // 根据聚合名称获取聚合结果
        Terms brandAgg = aggregations.get("brandAgg");
        // 获取buckets
        for (Terms.Bucket bucket : brandAgg.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("品牌名称: "+keyAsString);
        }


    }


    private void dealResult(SearchResponse search) {
        // 获取hits
        SearchHits hits = search.getHits();
        // 获取总条数
        long value = hits.getTotalHits().value;
        System.out.println("共 "+value+" 条数据");
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hit : hits1) {
            String sourceAsString = hit.getSourceAsString();
            Hotel hotel = JSON.parseObject(sourceAsString, Hotel.class);
            Map<String, HighlightField> hitHighlightFields = hit.getHighlightFields();
            HighlightField name = hitHighlightFields.get("name");
            String highLightName = name.getFragments()[0].string();
            hotel.setName(highLightName);
            System.out.println(hotel);
        }
    }
}
