package com.dk.server;

import com.dk.dao.anthora.TableaDao;
import com.dk.dao.anthor.TablebDao;
import com.dk.entity.TableA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BaseQueryServer {
    @Autowired
    private TableaDao tableaDao;
    @Autowired
    private TablebDao tablebDao;

    public String queryData(Long id){
        return tableaDao.findDataById(id);
    }
    public Long queryAddData(TableA tableA){
        return tableaDao.addData(tableA);
    }
    public Integer selectCount(){return tablebDao.selectCount();}
}
