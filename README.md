# 配置中心说明

## 现有项目问题

1. 将配置放入环境变量中，`profile`文件过于臃肿。
2. 关键敏感配置对开发人员可见，不安全。
3. 修改完配置，需要重启服务才能生效。

## 目标

1. 环境变量可控性要求，除了配置中心服务端与客户端的配置存入环境变量中，其余配置全部存放到配置中心中。
2. 所有配置对开发人员透明化，开发人员不需要关心配置如何传输、如何更新。
3. 配置管理交由运维人员以及项目负责人管理。
4. 配置自动加载、自动更新，配置可管理化。


## 项目结构

### config-center-server

> 此模块为配置中心服务端，主要功能如下：
> 1. 配置管理后台
> 2. 白名单管理
> 3. 远程配置获取

### config-center-client

> 此模块为配置中心的客户端，主要实现了`org.springframework.context.support.PropertySourcesPlaceholderConfigurer`。
>
> 根据性质不同，分为：
> 1. `NormalPropertySourcesPlaceholderConfigurer` 普通配置
> 2. `AutoReloadPropertySourcesPlaceholderConfigurer` 自动加载配置 

### config-center-common

> 配置中心公共包


## 示例


```xml
<bean id="propertyConfigurer" class="com.sgcai.config.center.client.properties.spring. NormalPropertySourcesPlaceholderConfigurer" init-method="init" destroy-method="destory">
	<property name="application" value="config-center-test"/>
	<property name="propertiesName" value="dui1dui-cashier"/>
	<property name="locations">
        <list>
            <value>classpath:config.properties</value>
        </list>
    </property>
</bean>


<bean id="propertyConfigurer" class="com.sgcai.config.center.client.properties.spring.AutoReloadPropertySourcesPlaceholderConfigurer" init-method="init" destroy-method="destory">
	<property name="application" value="config-center-test"/>
	<property name="propertiesName" value="dui1dui-cashier"/>
	<property name="locations">
        <list>
            <value>classpath:config.properties</value>
        </list>
    </property>
</bean>
```

## 注意事项

需要在环境变量中添加如下配置：

```sh
export DOCKER_CONFIG_CENTER_NOTIFY_ZOOKEEPER_HOST=localhost:2181

export DOCKER_CONFIG_CENTER_URL=http://localhost:8080
```