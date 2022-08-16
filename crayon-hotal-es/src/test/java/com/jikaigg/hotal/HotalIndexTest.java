package com.jikaigg.hotal;

import org.junit.Before;
import org.junit.Test;

public class HotalIndexTest {
    private RestHighLevelClient client;
    @Before
    void setUp(){
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.1.3:9200")
        ));
    }

}
