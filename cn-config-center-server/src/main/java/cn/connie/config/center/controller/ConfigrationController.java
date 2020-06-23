package cn.connie.config.center.controller;

import java.io.Writer;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.connie.config.center.common.utils.WebUtils;
import cn.connie.config.center.service.ConfigrationService;
import cn.connie.config.center.service.ModuleService;
import cn.connie.config.center.service.WhiteListService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.connie.config.center.common.CommonConstant;
import cn.connie.config.center.entity.Configration;
import cn.connie.config.center.entity.Module;
import cn.connie.config.center.entity.WhiteList;

@Controller
public class ConfigrationController {

    @Autowired
    private ConfigrationService configrationService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private WhiteListService whiteListService;

    @ResponseBody
    @RequestMapping("/{moduleEnName}.properties")
    public void config(@PathVariable("moduleEnName") String moduleEnName, HttpServletRequest request,
        HttpServletResponse response) throws Exception {

        Module module=moduleService.getModuleByEnName(moduleEnName);
        if (module == null) {
            return;
        }
        if (matchWhiteList(request)) {
            write(response, module);
            return;
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "");
    }

    private boolean matchWhiteList(HttpServletRequest request) {
        String ip= WebUtils.getIp(request);

        List<WhiteList> list=whiteListService.getAllWhiteLists();
        if (list.size() == 0) {
            return false;
        }
        for (WhiteList whiteList: list) {
            String temp=whiteList.getIp();
            temp=StringUtils.replace(temp, ".*", "");
            if (ip.startsWith(temp)) {
                return true;
            }
        }
        return false;
    }

    private Properties genBasicProperties(String moduleEnName, String version) {
        Properties prop=new Properties();
        prop.setProperty(CommonConstant.CONFIG_VERSION_CONFIG_KEY, version);
        return prop;
    }

    private void genProperties(String moduleId, Properties prop) {
        List<Configration> list=configrationService.getConfigrationByModuleId(moduleId);
        for (Configration config: list) {
            prop.setProperty(config.getKey(), config.getValue());
        }

        Module module=moduleService.getModuleById(moduleId);
        if (module != null) {
            genProperties(module.getParentId(), prop);
        }
    }

    private void write(HttpServletResponse response, Module module) {
        response.setContentType("text/plain;charset=utf-8");

        Properties prop=genBasicProperties(module.getEnName(), module.getConfigVersion());
        genProperties(module.getId(), prop);

        Writer writer=null;
        try {
            writer=response.getWriter();
            prop.store(writer, module.getName());
            writer.flush();
        } catch(Exception e) {
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch(Exception e) {
                }
            }
        }
    }
}
