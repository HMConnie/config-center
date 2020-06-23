package cn.connie.config.center.dao;

import java.util.List;

import cn.connie.config.center.entity.Module;

public interface ModuleDAO {

    Module selectOne(String id);

    Module selectByEnName(String enName);

    List<Module> selectList();

    List<Module> selectByParentId(String parentId);

    void update(Module module);

    void insert(Module module);
}
