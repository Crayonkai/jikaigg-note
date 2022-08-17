package com.jikaigg.hotal;

import com.alibaba.fastjson.JSON;
import com.jikaigg.hotal.pojo.Hotel;
import com.jikaigg.hotal.pojo.HotelDoc;
import com.jikaigg.hotal.service.IHotelService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.jikaigg.hotal.constants.HotalConstants.MAPPING_TEMPLATE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotalDocumentTest {
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
    public void test() {
        System.out.println(client);
    }

    /*
     * 新增文档
     * */
    @Test
    public void testCreateHotalIndex() throws IOException {
        Hotel hotel = iHotelService.getById(61083L);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        // 准备request对象
        IndexRequest request = new IndexRequest("hotal").id(hotelDoc.getId().toString());
        // 准备文档
        request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        // 发送请求
        client.index(request, RequestOptions.DEFAULT);
    }

    /*
     * 查询文档
     * */
    @Test
    public void testGetHotalIndex() throws IOException {
        // 准备request对象
        GetRequest request = new GetRequest("hotal", "61083");
        // 发送请求
        GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
        // 解析文档
        String source = documentFields.getSourceAsString();
        HotelDoc hotelDoc = JSON.parseObject(source, HotelDoc.class);
        System.out.println(hotelDoc);
    }

    /*
     * 修改文档
     * */
    @Test
    public void testPutHotalIndex() throws IOException {
        // 准备request对象
        UpdateRequest request = new UpdateRequest("hotal", "61083");
        request.doc(
                "price", "888"
        );
        // 发送请求
        UpdateResponse documentFields = client.update(request, RequestOptions.DEFAULT);
    }

    /*
     * 删除文档
     * */
    @Test
    public void testDeleteHotalIndex() throws IOException {
        // 准备request对象
        DeleteRequest request = new DeleteRequest("hotal", "61083");
        // 发送请求
        client.delete(request, RequestOptions.DEFAULT);
    }

    /*
     * 批量导入
     * */
    @Test
    public void testInHotalIndex() throws IOException {
        List<Hotel> list = iHotelService.list();
        // 准备Bulkrequest对象
        BulkRequest request = new BulkRequest();
        // 将数据库对象转换为文档类型
        for (Hotel hotel : list) {
            HotelDoc hotelDoc = new HotelDoc(hotel);
            request.add(new IndexRequest("hotal")
                    .id(hotelDoc.getId()
                            .toString())
                    .source(JSON.toJSONString(hotelDoc), XContentType.JSON));
        }
        // 发送请求
        client.bulk(request, RequestOptions.DEFAULT);
    }


}

