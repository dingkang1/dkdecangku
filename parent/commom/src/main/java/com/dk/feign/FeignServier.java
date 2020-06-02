package com.dk.feign;

import feign.*;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;


@Component
public class FeignServier {

    public interface FeignServierApi {
        @RequestLine("GET /base/queryData")
        @Headers({"Content-Type: application/json"})
        Response batchRegister(@RequestParam("id") int id);

    }


    public <T> T invoke(String url ,Class<T> apiClazz){
        T api = Feign.builder().options(new Request.Options(3000, 10000))
                .retryer(Retryer.NEVER_RETRY).encoder(new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {
                    @Override
                    public HttpMessageConverters getObject() throws BeansException {
                        return new HttpMessageConverters();
                    }
                }))
                .decoder(new JacksonDecoder()).logger(new Slf4jLogger()).logLevel(Logger.Level.FULL)
                .target(apiClazz, url);
        return api ;
    }

    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(1);
        for(int i = 0 ; i<1001;i++ ) {
            objectObjectHashMap.put(i,i);
        }
    }

}
