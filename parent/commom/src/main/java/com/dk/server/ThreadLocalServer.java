package com.dk.server;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class ThreadLocalServer {

    private ThreadLocal<ConcurrentHashMap<String,Object>> mapThreadLocal  = new ThreadLocal(){};

    public final static String DATA = "data" ;


    public Object get(String key){
        return mapThreadLocal.get()==null?null:mapThreadLocal.get().get(key);
    }

    public Object put(String key , Object o){
        if(mapThreadLocal.get() ==null ){
            mapThreadLocal.set(new ConcurrentHashMap<String,Object>());
        }
        return mapThreadLocal.get().put(key,o);
    }
}
