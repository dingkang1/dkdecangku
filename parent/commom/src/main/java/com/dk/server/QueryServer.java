package com.dk.server;

import com.dk.entity.ResultVo;
import com.dk.feign.FeignServier;
import com.dk.feign.inter.BasicFeign;
import com.dk.utils.CommonServerUtils;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QueryServer {

    @Autowired
    FeignServier feignServier;

    @Autowired
    BasicFeign basicFeign;
    @Autowired
    ThreadLocalServer threadLocalServer;

    public Object quertData(int id){
        FeignServier.FeignServierApi invoke = feignServier.invoke("http://localhost:1112", FeignServier.FeignServierApi.class);
        Response response = invoke.batchRegister(id);
        ResultVo<String> resultVo = CommonServerUtils.getResultVo(response, String.class);
        return resultVo ;
    }

    public Object quertData1(Long id){
        Response response = basicFeign.queryData(id);
        return threadLocalServer.get(ThreadLocalServer.DATA) ;
    }

}
