package com.sgcai.config.center.entity;

import com.sgcai.config.center.common.base.BasicTO;

import java.util.Date;


/**
 * 模块
 * 
 * @author shixuelin
 */
public class Module extends BasicTO {

  private static final long serialVersionUID = 1L;

  /**
   * ID
   */
  private String id;

  /**
   * 名称
   */
  private String name;

  /**
   * 英文名
   */
  private String enName;

  /**
   * 父模块ID
   */
  private String parentId;

  /**
   * 描述
   */
  private String description;

  /**
   * 配置版本号
   */
  private String configVersion;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 上次更细时间
   */
  private Date lastUpdateTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getConfigVersion() {
    return configVersion;
  }

  public void setConfigVersion(String configVersion) {
    this.configVersion = configVersion;
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
}
