package com.sgcai.config.center.notify;

import java.util.List;

import com.sgcai.config.center.entity.Consumer;

public interface Notifier {

    void init() throws Exception;

    void destory();

    void notify(String moduleEnName, String configVersion) throws Exception;

    List<Consumer> getAllConsumer(String moduleEnName);
}
