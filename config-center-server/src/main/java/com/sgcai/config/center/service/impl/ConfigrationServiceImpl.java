package com.sgcai.config.center.service.impl;

import java.util.List;

import com.sgcai.config.center.Constant;
import com.sgcai.config.center.common.utils.Dui1DuiStringUtils;
import com.sgcai.config.center.dao.ConfigrationDAO;
import com.sgcai.config.center.dao.ModuleDAO;
import com.sgcai.config.center.entity.Configration;
import com.sgcai.config.center.entity.Module;
import com.sgcai.config.center.exception.ConfigrationExistException;
import com.sgcai.config.center.exception.ConfigrationNotException;
import com.sgcai.config.center.exception.ModuleNotExistException;
import com.sgcai.config.center.notify.ZookeeperNotifier;
import com.sgcai.config.center.service.ConfigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("configrationService")
public class ConfigrationServiceImpl implements ConfigrationService {

  @Autowired
  private ConfigrationDAO configrationDAO;

  @Autowired
  private ModuleDAO moduleDAO;

  @Autowired
  private ZookeeperNotifier zookeeperNotifier;

  public List<Configration> getConfigrationByModuleId(String moduleId) {
    return configrationDAO.selectByModuleId(moduleId);
  }

  public Configration getConfigrationById(String id) {
    return configrationDAO.selectOne(id);
  }

  public void addConfigration(Configration config) throws ConfigrationExistException {
    Configration temp = configrationDAO.selectByKey(config.getKey());
    if (temp != null) {
      throw new ConfigrationExistException();
    }
    config.setId(Dui1DuiStringUtils.generateUUID());
    configrationDAO.insert(config);
    notify(config.getModuleId());
  }

  public void updateConfigration(Configration config) {
    configrationDAO.update(config);
    notify(config.getModuleId());
  }

  public void moveConfigration(String configId, String moduleId) throws ConfigrationNotException,
          ModuleNotExistException {
    Configration config = configrationDAO.selectOne(configId);
    if (config == null) {
      throw new ConfigrationNotException();
    }
    String oldModuleId = config.getModuleId();

    Module module = moduleDAO.selectOne(moduleId);
    if (!moduleId.equals(Constant.DEFAULT_ROOT_MODULE_ID) && module == null) {
      throw new ModuleNotExistException();
    }
    config.setModuleId(moduleId);
    configrationDAO.update(config);
    notify(oldModuleId);
    notify(moduleId);
  }

  public void deleteConfigration(String id) {
    Configration config = configrationDAO.selectOne(id);
    notify(config.getModuleId());
    configrationDAO.delete(id);
  }

  private void notify(String moduleId) {
    if (!moduleId.equals(Constant.DEFAULT_ROOT_MODULE_ID)) {
      Module module = moduleDAO.selectOne(moduleId);
      module.setConfigVersion(Dui1DuiStringUtils.generateOrderNo());
      moduleDAO.update(module);
      try {
        zookeeperNotifier.notify(module.getEnName(), module.getConfigVersion());
      } catch (Exception e) {
      }
    }
    List<Module> children = moduleDAO.selectByParentId(moduleId);
    for (Module child : children) {
      notify(child.getId());
    }
  }
}
