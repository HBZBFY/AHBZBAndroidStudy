package com.feiyue.gulimail.product.feign;

import com.feiyue.common.to.es.SkuEsModel;
import com.feiyue.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mail-search")
public interface ElasticSearchFeignService {
    @PostMapping("/elasticsearch/product")
    R saveProduct(@RequestBody List<SkuEsModel> skuEsModel) throws Exception;
}
