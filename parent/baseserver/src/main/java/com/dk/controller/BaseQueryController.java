package com.dk.controller;

import com.dk.entity.TableA;
import com.dk.server.BaseQueryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseQueryController {
    @Autowired
    private BaseQueryServer baseQueryServer;

    @GetMapping("/queryData")
    public String queryData(@RequestParam("id") Long id){
        return baseQueryServer.queryData(id);
    }
    @GetMapping("/addData")
    public Long queryAddData(){
        TableA tableA = new TableA();
        tableA.setColumnC("22");
        tableA.setColumnB("丁康");
        return baseQueryServer.queryAddData(tableA);
    }
    @GetMapping("/selectCount")
    public Integer selectCount(){
        return baseQueryServer.selectCount();
    }
}
