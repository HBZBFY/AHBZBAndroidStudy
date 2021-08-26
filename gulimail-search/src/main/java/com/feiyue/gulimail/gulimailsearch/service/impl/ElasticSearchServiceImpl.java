package com.feiyue.gulimail.gulimailsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.feiyue.common.to.es.SkuEsModel;
import com.feiyue.gulimail.gulimailsearch.config.ElasticsearchConfig;
import com.feiyue.gulimail.gulimailsearch.constant.EsConstant;
import com.feiyue.gulimail.gulimailsearch.service.ElasticSearchService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("ElasticSearchService")
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Override
    public void productStatusUp(List<SkuEsModel> skuEsModel) throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel esModel : skuEsModel) {
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(esModel.getSkuId().toString());
            String s = JSONObject.toJSONString(esModel);
            indexRequest.source(s, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        restHighLevelClient.bulk(bulkRequest, ElasticsearchConfig.COMMON_OPTIONS);
    }
}
