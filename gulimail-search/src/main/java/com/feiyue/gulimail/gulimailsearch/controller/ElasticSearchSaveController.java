package com.feiyue.gulimail.gulimailsearch.controller;

import com.feiyue.common.to.es.SkuEsModel;
import com.feiyue.common.utils.R;
import com.feiyue.common.vo.SkuHasStockVo;
import com.feiyue.gulimail.gulimailsearch.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/elasticsearch")
public class ElasticSearchSaveController {

    @Autowired
    private ElasticSearchService elasticSearchService;
    @PostMapping("/product")
    public R saveProduct(@RequestBody List<SkuEsModel> skuEsModel) throws Exception {
        elasticSearchService.productStatusUp(skuEsModel);
        return R.ok();
    }
}
