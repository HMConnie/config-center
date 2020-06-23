package cn.connie.config.center.controller.manager;

import java.util.HashMap;
import java.util.Map;

import cn.connie.config.center.entity.WhiteList;
import cn.connie.config.center.exception.WhiteListExistException;
import cn.connie.config.center.service.WhiteListService;
import cn.connie.config.center.util.CallbackType;
import cn.connie.config.center.util.DWZUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manager/whitelist")
public class WhiteListController {

    @Autowired
    private WhiteListService whiteListService;

    @RequestMapping("/list.html")
    public ModelAndView list() {
        Map<String, Object> model=new HashMap<String, Object>();
        model.put("list", whiteListService.getAllWhiteLists());
        return new ModelAndView("whitelist/list", "model", model);
    }

    @RequestMapping("/go_add_pager.html")
    public ModelAndView goAddPager() {
        return new ModelAndView("whitelist/add");
    }

    @ResponseBody
    @RequestMapping("/add.html")
    public String addWhiteList(@RequestParam("ip") String ip, @RequestParam("description") String description) {
        WhiteList list=new WhiteList();
        list.setDescription(description);
        list.setIp(ip);
        try {
            whiteListService.addWhiteList(list);
        } catch(WhiteListExistException e) {
            return DWZUtils.ajaxForwardError("IP已存在");
        }
        return DWZUtils.ajaxForwardSuccess("添加成功", "white_list_manager", null, CallbackType.closeCurrent);
    }

    @ResponseBody
    @RequestMapping("/delete.html")
    public String delete(@RequestParam("id") String id) {
        whiteListService.delete(id);
        return DWZUtils.ajaxForwardSuccess("删除成功", "white_list_manager", null, null);
    }
}
