package com.sgcai.config.center.controller.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sgcai.config.center.entity.Configration;
import com.sgcai.config.center.entity.Module;
import com.sgcai.config.center.exception.ConfigrationExistException;
import com.sgcai.config.center.exception.ConfigrationNotException;
import com.sgcai.config.center.exception.ModuleNotExistException;
import com.sgcai.config.center.service.ConfigrationService;
import com.sgcai.config.center.service.ModuleService;
import com.sgcai.config.center.util.CallbackType;
import com.sgcai.config.center.util.DWZUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("managerConfigrationController")
@RequestMapping("/manager/configration")
public class ConfigrationController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ConfigrationService configrationService;

    @RequestMapping("/go_move_pager.html")
    public ModelAndView goMovePager(@RequestParam("id") String id) {
        Map<String, Object> model=new HashMap<String, Object>();

        Configration config=configrationService.getConfigrationById(id);
        model.put("config", config);

        List<Module> list=moduleService.getAllModule();
        model.put("modules", list);
        return new ModelAndView("config/move", "model", model);
    }

    @ResponseBody
    @RequestMapping("/move.html")
    public String move(@RequestParam("id") String id, @RequestParam("moduleId") String moduleId) {
        try {
            configrationService.moveConfigration(id, moduleId);
            return DWZUtils.ajaxForwardSuccess("移动成功", "module_list", null, CallbackType.closeCurrent);
        } catch(ConfigrationNotException e) {
            return DWZUtils.ajaxForwardError("配置不存在");
        } catch(ModuleNotExistException e) {
            return DWZUtils.ajaxForwardError("模块不存在");
        }
    }

    @RequestMapping("/go_add_pager.html")
    public ModelAndView goAddPager(@RequestParam("moduleId") String moduleId) {
        Map<String, Object> model=new HashMap<String, Object>();
        model.put("moduleId", moduleId);
        return new ModelAndView("config/add", "model", model);
    }

    @ResponseBody
    @RequestMapping("/add.html")
    public String addConfigration(@RequestParam("moduleId") String moduleId, @RequestParam("key") String key,
        @RequestParam("value") String value, @RequestParam("description") String description) {
        Configration config=new Configration();
        config.setDescription(description);
        config.setKey(key);
        config.setModuleId(moduleId);
        config.setValue(value);
        try {
            configrationService.addConfigration(config);
        } catch(ConfigrationExistException e) {
            return DWZUtils.ajaxForwardError("配置已存在");
        }
        return DWZUtils.ajaxForwardSuccess("添加成功", "module_list", null, CallbackType.closeCurrent);
    }

    @RequestMapping("/go_modify_pager.html")
    public ModelAndView goModifyPager(@RequestParam("id") String id) {
        Map<String, Object> model=new HashMap<String, Object>();
        Configration config=configrationService.getConfigrationById(id);
        model.put("config", config);
        return new ModelAndView("config/modify", "model", model);
    }

    @ResponseBody
    @RequestMapping("/modify.html")
    public String modifyConfigration(@RequestParam("id") String id, @RequestParam("value") String value) {
        Configration config=configrationService.getConfigrationById(id);
        if (config == null) {
            return DWZUtils.ajaxForwardError("配置不存在");
        }
        config.setValue(value);
        configrationService.updateConfigration(config);
        return DWZUtils.ajaxForwardSuccess("修改成功", "module_list", null, CallbackType.closeCurrent);
    }

    @ResponseBody
    @RequestMapping("/delete.html")
    public String delete(@RequestParam("id") String id) {
        configrationService.deleteConfigration(id);
        return DWZUtils.ajaxForwardSuccess("删除成功", "module_list", null, null);
    }
}
