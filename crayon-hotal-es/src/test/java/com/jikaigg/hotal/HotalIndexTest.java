package com.jikaigg.hotal;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.jikaigg.hotal.constants.HotalConstants.MAPPING_TEMPLATE;

public class HotalIndexTest {
    private RestHighLevelClient client;
    @Before
    public void setUp(){
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.1.3:9200")
        ));
    }
    @After
    public void tearDown() throws IOException {
        this.client.close();
    }
    @Test
    public void test(){
        System.out.println(client);
    }

    /*
    * 创建索引库
    * */
    @Test
    public void testCreateHotalIndex() throws IOException {
        // 创建request对象
        CreateIndexRequest request = new CreateIndexRequest("hotal");
        // 请求参数，MAPPING_TEMPLATE是静态常量字符串DSL语句
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);

    }

    /*
    * 删除索引库
    * */
    @Test
    public void testDeleteHotalIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("hotal");
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    /*
    * 判断索引库是否存在
    * */
    @Test
    public void testExistHotalIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("hotal");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists?"索引库存在":"索引库不存在");
    }

}
