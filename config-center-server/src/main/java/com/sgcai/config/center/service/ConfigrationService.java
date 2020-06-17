package com.sgcai.config.center.service;

import java.util.List;

import com.sgcai.config.center.entity.Configration;
import com.sgcai.config.center.exception.ConfigrationExistException;
import com.sgcai.config.center.exception.ConfigrationNotException;
import com.sgcai.config.center.exception.ModuleNotExistException;

/**
 * @author shixuelin
 *
 */
public interface ConfigrationService {

    /**
     * 根据模块ID获取配置信息
     * @param moduleId
     * @return
     */
    List<Configration> getConfigrationByModuleId(String moduleId);

    /**
     * 根据配置ID获取配置项
     * @param id
     * @return
     */
    Configration getConfigrationById(String id);

    /**
     * 添加配置
     * @param config
     * @throws ConfigrationExistException
     */
    void addConfigration(Configration config) throws ConfigrationExistException;

    /**
     * 修改配置
     * @param config
     */
    void updateConfigration(Configration config);

    /**
     * 删除配置
     * @param id
     */
    void deleteConfigration(String id);

    /**
     * 移动配置
     * @param configId
     * @param moduleId
     * @throws ConfigrationNotException
     * @throws ModuleNotExistException
     */
    void moveConfigration(String configId, String moduleId) throws ConfigrationNotException, ModuleNotExistException;
}
