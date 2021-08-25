package com.feiyue.gulimail.gulimailsearch;

import com.alibaba.fastjson.JSON;
import com.feiyue.gulimail.gulimailsearch.config.ElasticsearchConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@org.springframework.boot.test.context.SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootTest1 {

    @Autowired
    private RestHighLevelClient client;
    @org.junit.Test
    public void test1() throws Exception{
        IndexRequest indexRequest = new IndexRequest("user");
        User user = new User();
        user.setName("feiyue");
        user.setAge(11);
        user.setPassword("123");
        String json = JSON.toJSONString(user);
        indexRequest.id("id").source(json, XContentType.JSON);
        client.index(indexRequest, ElasticsearchConfig.COMMON_OPTIONS);
        System.out.println(client);
    }




    @Data
    class User {
        private String name;
        private Integer age;
        private String password;
    }
}
