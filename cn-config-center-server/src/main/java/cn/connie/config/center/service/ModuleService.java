package cn.connie.config.center.service;

import java.util.List;

import cn.connie.config.center.entity.Module;
import cn.connie.config.center.exception.ModuleExistException;
import cn.connie.config.center.exception.ParentModuleNotExistException;

public interface ModuleService {

    /**
     * 获取全部模块 
     * @return
     */

    List<Module> getAllModule();

    /**
     * 根据父ID获取模块
     * @param parentId
     * @return
     */
    List<Module> getModuleByParentId(String parentId);

    /**
     * 根据ID获取模块
     * @param id
     * @return
     */
    Module getModuleById(String id);

    /**
     * 根据英文名获取模块
     * @param enName
     * @return
     */
    Module getModuleByEnName(String enName);

    /**
     * 添加模块
     * @param module
     * @throws ModuleExistException
     * @throws ParentModuleNotExistException
     */
    void addModule(Module module) throws ModuleExistException, ParentModuleNotExistException;

    /**
     * 修改模块
     * @param module
     */
    void modifyModule(Module module);
}
