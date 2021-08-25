package com.feiyue.gulimail.gulimailsearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feiyue.gulimail.gulimailsearch.config.ElasticsearchConfig;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
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

    @org.junit.Test
    public void test2() throws Exception{
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.aggregation(AggregationBuilders.terms("name").field("name.keyword").size(10));
        searchSourceBuilder.aggregation(AggregationBuilders.avg("age").field("age"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, ElasticsearchConfig.COMMON_OPTIONS);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String string = hit.getSourceAsString();
            User user = JSONObject.parseObject(string, User.class);
            System.out.println(user);
        }
        Aggregations aggregations = response.getAggregations();
        Terms name = aggregations.get("name");
        for (Terms.Bucket bucket : name.getBuckets()) {
            System.out.println(bucket.getKeyAsString());
        }
        Avg avg = aggregations.get("age");
        System.out.println(avg.getValueAsString());
        System.out.println(response.toString());
    }



    @Data
    @ToString
    static class User {
        private String name;
        private Integer age;
        private String password;
    }
}
