package cn.connie.config.center.service;

import java.util.List;

import cn.connie.config.center.exception.WhiteListExistException;
import cn.connie.config.center.entity.WhiteList;

public interface WhiteListService {

    /**
     * 获取所有白名单列表
     * @return
     */
    List<WhiteList> getAllWhiteLists();

    /**
     * 根据IP获取白名单
     * @param ip
     * @return
     */
    WhiteList getWhiteListByIp(String ip);

    /**
     * 添加白名单
     * @param list
     * @throws WhiteListExistException
     */
    void addWhiteList(WhiteList list) throws WhiteListExistException;

    /**
     * 删除白名单
     * @param id
     */
    void delete(String id);
}
