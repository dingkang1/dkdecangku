package com.dk.controller;

import com.dk.entity.ResultVo;
import com.dk.server.QueryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @Autowired
    QueryServer queryServer ;


    @GetMapping("/query")
    public Object query(@RequestParam("id") Long id){
        return  queryServer.quertData1(id);
    }
    @GetMapping("/query1")
    public Object query1(@RequestParam("id") int id){
        return queryServer.quertData(id);
    }
}
