package com.sgcai.config.center.controller.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sgcai.config.center.Constant;
import com.sgcai.config.center.entity.Consumer;
import com.sgcai.config.center.entity.Module;
import com.sgcai.config.center.exception.ModuleExistException;
import com.sgcai.config.center.exception.ParentModuleNotExistException;
import com.sgcai.config.center.notify.ZookeeperNotifier;
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

@Controller
@RequestMapping("/manager/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ConfigrationService configrationService;

    @Autowired
    private ZookeeperNotifier zookeeperNotifier;

    @RequestMapping("/consumers.html")
    public ModelAndView consumers(@RequestParam(value="moduleId") String moduleId) {
        Map<String, Object> model=new HashMap<String, Object>();
        Module module=moduleService.getModuleById(moduleId);
        List<Consumer> list=zookeeperNotifier.getAllConsumer(module.getEnName());
        model.put("consumers", list);
        model.put("configVersion", module.getConfigVersion());
        return new ModelAndView("module/consumers", "model", model);
    }

    @RequestMapping("/list.html")
    public ModelAndView list(
        @RequestParam(value="moduleId", defaultValue= Constant.DEFAULT_ROOT_MODULE_ID) String moduleId) {
        Map<String, Object> model=new HashMap<String, Object>();

        model.put("moduleId", moduleId);
        StringBuilder sb=new StringBuilder();
        genModuleTree(null, sb);
        model.put("treeHtml", sb.toString());
        return new ModelAndView("module/list", "model", model);
    }

    @RequestMapping("/detail.html")
    public ModelAndView detail(@RequestParam("id") String id) {
        Map<String, Object> model=new HashMap<String, Object>();

        Module module=moduleService.getModuleById(id);
        model.put("config", configrationService.getConfigrationByModuleId(id));
        model.put("id", id);
        model.put("moduleEnName", id.equals(Constant.DEFAULT_ROOT_MODULE_ID) ? "" : module.getEnName());
        model.put("moduleName", id.equals(Constant.DEFAULT_ROOT_MODULE_ID) ? "主模块" : module.getName());
        return new ModelAndView("module/detail", "model", model);
    }

    @RequestMapping("/go_move_pager.html")
    public ModelAndView goMovePager(@RequestParam("id") String id) {
        Map<String, Object> model=new HashMap<String, Object>();
        Module module=moduleService.getModuleById(id);
        model.put("module", module);
        
        List<Module> list=moduleService.getAllModule();
        model.put("modules", list);
        
        return new ModelAndView("module/move", "model", model);
    }

    @ResponseBody
    @RequestMapping("move.html")
    public String modify(@RequestParam("id") String id, @RequestParam("parentId") String parentId) {
        Module module=moduleService.getModuleById(id);
        module.setParentId(parentId);
        moduleService.modifyModule(module);
        return DWZUtils.ajaxForwardSuccess("移动成功", "module_list", null, CallbackType.closeCurrent);
    }

    @RequestMapping("/go_modify_pager.html")
    public ModelAndView goModifyPager(@RequestParam("id") String id) {
        Map<String, Object> model=new HashMap<String, Object>();
        Module module=moduleService.getModuleById(id);
        model.put("module", module);
        return new ModelAndView("module/modify", "model", model);
    }

    @ResponseBody
    @RequestMapping("modify.html")
    public String modify(@RequestParam("id") String id, @RequestParam("name") String name,
        @RequestParam("description") String description) {
        Module module=moduleService.getModuleById(id);
        module.setName(name);
        module.setDescription(description);
        moduleService.modifyModule(module);
        return DWZUtils.ajaxForwardSuccess("修改成功", "module_list", null, CallbackType.closeCurrent);
    }

    @RequestMapping("/go_add_pager.html")
    public ModelAndView goAddPager(@RequestParam("parentId") String parentId) {
        Map<String, Object> model=new HashMap<String, Object>();
        model.put("parentId", parentId);
        return new ModelAndView("module/add", "model", model);
    }

    @ResponseBody
    @RequestMapping("add.html")
    public String addModule(@RequestParam("parentId") String parentId, @RequestParam("name") String name,
        @RequestParam("enName") String enName, @RequestParam("description") String description) {
        Module module=new Module();
        module.setDescription(description);
        module.setEnName(enName);
        module.setName(name);
        module.setParentId(parentId);
        try {
            moduleService.addModule(module);
        } catch(ModuleExistException e) {
            return DWZUtils.ajaxForwardError("模块已存在");
        } catch(ParentModuleNotExistException e) {
            return DWZUtils.ajaxForwardError("父模块不存在");
        }
        return DWZUtils.ajaxForwardSuccess("添加成功", "module_list", null, CallbackType.closeCurrent);
    }

    private void genModuleTree(Module module, StringBuilder sb) {
        if (module == null) {
            module=new Module();
            module.setId(Constant.DEFAULT_ROOT_MODULE_ID);
            module.setName("主模块");
        }

        sb.append("<li><a target=\"ajax\" rel=\"jbsxBox\" href=\"manager/module/detail.html?id=")
            .append(module.getId()).append("\">").append(module.getName()).append("</a>");

        List<Module> list=moduleService.getModuleByParentId(module.getId());
        if (list.size() == 0) {
            sb.append("</li>");
            return;
        }
        sb.append("<ul>");
        for (Module child: list) {
            genModuleTree(child, sb);
        }
        sb.append("</ul>");
        sb.append("</li>");
    }
}
