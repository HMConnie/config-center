package com.sgcai.config.center.service.impl;

import java.util.List;

import com.sgcai.config.center.common.utils.Dui1DuiStringUtils;
import com.sgcai.config.center.dao.WhiteListDAO;
import com.sgcai.config.center.entity.WhiteList;
import com.sgcai.config.center.exception.WhiteListExistException;
import com.sgcai.config.center.service.WhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("whiteListService")
public class WhiteListServiceImpl implements WhiteListService {

  @Autowired
  private WhiteListDAO whiteListDAO;

  public WhiteList getWhiteListByIp(String ip) {
    return whiteListDAO.selectByIp(ip);
  }

  public void addWhiteList(WhiteList list) throws WhiteListExistException {
    WhiteList temp = getWhiteListByIp(list.getIp());
    if (temp != null) {
      throw new WhiteListExistException();
    }
    list.setId(Dui1DuiStringUtils.generateUUID());
    whiteListDAO.insert(list);
  }

  public void delete(String id) {
    whiteListDAO.delete(id);
  }

  public List<WhiteList> getAllWhiteLists() {
    return whiteListDAO.selectList();
  }
}
