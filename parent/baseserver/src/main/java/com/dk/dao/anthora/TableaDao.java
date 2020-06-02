package com.dk.dao.anthora;

import com.dk.entity.TableA;
import org.apache.ibatis.annotations.Param;

public interface TableaDao {
    String findDataById(@Param("id") Long id);
    Long addData(@Param("tableA")TableA tableA);
}
