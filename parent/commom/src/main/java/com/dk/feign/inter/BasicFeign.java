package com.dk.feign.inter;

import com.dk.FallbackFactory.BasicFeignFallBackFactory;
import com.dk.annotiation.ResponseDataType;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="baseserver" ,fallbackFactory = BasicFeignFallBackFactory.class)
public interface BasicFeign {

    @GetMapping("/base/queryData")
    @ResponseDataType
    Response queryData(@RequestParam("id") Long id);

    @GetMapping("/base/addData")
    Response addData();
}
