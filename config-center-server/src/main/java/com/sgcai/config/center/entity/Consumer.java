package com.sgcai.config.center.entity;


import com.sgcai.config.center.common.base.BasicTO;

public class Consumer extends BasicTO {

  private static final long serialVersionUID = 1L;

  private String application;

  private Integer pid;

  private String host;

  private String configVersion;

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getConfigVersion() {
    return configVersion;
  }

  public void setConfigVersion(String configVersion) {
    this.configVersion = configVersion;
  }

}
