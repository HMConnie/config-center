package cn.connie.config.center.dao;

import java.util.List;

import cn.connie.config.center.entity.Configration;

public interface ConfigrationDAO {

    Configration selectOne(String id);

    Configration selectByKey(String key);

    List<Configration> selectByModuleId(String moduleId);

    void insert(Configration config);

    void update(Configration config);

    void delete(String id);
}
