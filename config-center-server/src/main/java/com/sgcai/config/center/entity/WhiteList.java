package com.sgcai.config.center.entity;

import com.sgcai.config.center.common.base.BasicTO;

import java.util.Date;


/**
 * 白名单
 * 
 * @author shixuelin
 */
public class WhiteList extends BasicTO {

  private static final long serialVersionUID = 1L;

  /**
   * ID
   */
  private String id;

  /**
   * IP
   */
  private String ip;

  /**
   * 描述
   */
  private String description;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 上次修改时间
   */
  private Date lastUpdateTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
