package com.dk.utils;

import com.alibaba.fastjson.JSONObject;
import com.dk.entity.ResultVo;
import com.dk.feign.FeignServier;
import feign.Response;
import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class CommonServerUtils {

    private static Logger logger = LoggerFactory.getLogger(FeignServier.class);
    public static <T> ResultVo<T> getResultVo(Response response, Class<T> tClass){
        Response.Body body = response.body();
        CharBuffer charBuffer = CharBuffer.allocate(body.length());
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(String.valueOf(response.status()));
        resultVo.setMsg(response.status()!=200?response.reason():"success");
        Reader reader = null;
        try {
            reader = body.asReader(Charsets.UTF_8);
            reader.read(charBuffer);
            charBuffer.flip();
            if(tClass == String.class){
                resultVo.setData(new String(charBuffer.array()));
            }else{
                resultVo.setData(JSONObject.parseObject(new String(charBuffer.array()),tClass));
            }
            charBuffer.clear();
        } catch (IOException e) {
            logger.error("读取数据是发生异常，异常信息如下:[{}]",e.getMessage());
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return resultVo;
    }
}
