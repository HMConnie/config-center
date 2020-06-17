package com.sgcai.config.center.dao;

import java.util.List;

import com.sgcai.config.center.entity.WhiteList;

public interface WhiteListDAO {

    WhiteList selectOne(String id);

    WhiteList selectByIp(String ip);

    List<WhiteList> selectList();

    void delete(String id);

    void insert(WhiteList entity);
}
