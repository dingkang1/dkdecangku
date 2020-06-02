package com.dk.FallbackFactory;

import com.dk.entity.ResultVo;
import com.dk.feign.inter.BasicFeign;
import feign.Response;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class BasicFeignFallBackFactory implements FallbackFactory<BasicFeign> {

    @Override
    public BasicFeign create(Throwable throwable) {

        return new BasicFeign() {
            @Override
            public Response queryData(Long id) {
                ResultVo resultVo = new ResultVo();
                resultVo.setMsg(throwable.getMessage());
                resultVo.setCode("10001");
                return null;
            }

            @Override
            public Response addData() {
                ResultVo resultVo = new ResultVo();
                resultVo.setMsg(throwable.getMessage());
                resultVo.setCode("10001");
                return null;
            }
        };
    }
}
