package com.feiyue.gulimail.gulimailsearch.service;

import com.feiyue.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ElasticSearchService {
    void productStatusUp(List<SkuEsModel> skuEsModel) throws IOException, Exception;
}
