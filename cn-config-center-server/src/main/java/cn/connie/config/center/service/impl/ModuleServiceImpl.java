package cn.connie.config.center.service.impl;

import java.util.List;

import cn.connie.config.center.Constant;
import cn.connie.config.center.common.utils.Dui1DuiStringUtils;
import cn.connie.config.center.dao.ModuleDAO;
import cn.connie.config.center.entity.Module;
import cn.connie.config.center.exception.ModuleExistException;
import cn.connie.config.center.exception.ParentModuleNotExistException;
import cn.connie.config.center.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

  @Autowired
  private ModuleDAO moduleDAO;

  public List<Module> getModuleByParentId(String parentId) {
    return moduleDAO.selectByParentId(parentId);
  }

  public Module getModuleById(String id) {
    return moduleDAO.selectOne(id);
  }

  public List<Module> getAllModule() {
    return moduleDAO.selectList();
  }

  public Module getModuleByEnName(String enName) {
    return moduleDAO.selectByEnName(enName);
  }

  public void addModule(Module module) throws ModuleExistException, ParentModuleNotExistException {
    Module temp = getModuleByEnName(module.getEnName());
    if (temp != null) {
      throw new ModuleExistException();
    }
    Module parent = getModuleById(module.getParentId());
    if (!module.getParentId().equals(Constant.DEFAULT_ROOT_MODULE_ID) && parent == null) {
      throw new ParentModuleNotExistException();
    }
    module.setId(Dui1DuiStringUtils.generateUUID());
    module.setConfigVersion(Dui1DuiStringUtils.generateOrderNo());
    moduleDAO.insert(module);
  }

  public void modifyModule(Module module) {
    moduleDAO.update(module);
  }
}
