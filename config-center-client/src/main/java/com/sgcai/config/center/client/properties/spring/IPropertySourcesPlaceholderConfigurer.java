package com.sgcai.config.center.client.properties.spring;

public interface IPropertySourcesPlaceholderConfigurer {

    void init() throws Exception;

    void destory();

    void reloadProperties() throws Exception;
}
