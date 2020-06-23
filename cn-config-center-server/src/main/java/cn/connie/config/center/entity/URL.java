package cn.connie.config.center.entity;

import cn.connie.config.center.common.base.BasicTO;

import java.util.HashMap;
import java.util.Map;


public class URL extends BasicTO {

  private static final long serialVersionUID = 1L;

  private String protocol;

  private String host;

  private int port;

  private String path;

  public URL(String url) {
    if (url == null || (url = url.trim()).length() == 0) {
      throw new IllegalArgumentException("url == null");
    }
    String protocol = null;
    String username = null;
    String host = null;
    int port = 0;
    String path = null;
    Map<String, String> parameters = null;
    int i = url.indexOf("?"); // seperator between body and parameters
    if (i >= 0) {
      String[] parts = url.substring(i + 1).split("\\&");
      parameters = new HashMap<String, String>();
      for (String part : parts) {
        part = part.trim();
        if (part.length() > 0) {
          int j = part.indexOf('=');
          if (j >= 0) {
            parameters.put(part.substring(0, j), part.substring(j + 1));
          } else {
            parameters.put(part, part);
          }
        }
      }
      url = url.substring(0, i);
    }
    i = url.indexOf("://");
    if (i >= 0) {
      if (i == 0)
        throw new IllegalStateException("url missing protocol: \"" + url + "\"");
      protocol = url.substring(0, i);
      url = url.substring(i + 3);
    } else {
      // case: file:/path/to/file.txt
      i = url.indexOf(":/");
      if (i >= 0) {
        if (i == 0)
          throw new IllegalStateException("url missing protocol: \"" + url + "\"");
        protocol = url.substring(0, i);
        url = url.substring(i + 1);
      }
    }

    i = url.indexOf("/");
    if (i >= 0) {
      path = url.substring(i + 1);
      url = url.substring(0, i);
    }
    i = url.indexOf("@");
    if (i >= 0) {
      username = url.substring(0, i);
      int j = username.indexOf(":");
      if (j >= 0) {
        username = username.substring(0, j);
      }
      url = url.substring(i + 1);
    }
    i = url.indexOf(":");
    if (i >= 0 && i < url.length() - 1) {
      port = Integer.parseInt(url.substring(i + 1));
      url = url.substring(0, i);
    }
    if (url.length() > 0) {
      host = url;
    }
    this.host = host;
    this.protocol = protocol;
    this.port = port;
    this.path = path;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

}
