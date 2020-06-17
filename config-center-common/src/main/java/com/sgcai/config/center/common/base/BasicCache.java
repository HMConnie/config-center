package com.sgcai.config.center.common.base;

public interface BasicCache<R, K> {

    String genCacheKey(K key);

    R load(K key);

    void delete(K key);
}
