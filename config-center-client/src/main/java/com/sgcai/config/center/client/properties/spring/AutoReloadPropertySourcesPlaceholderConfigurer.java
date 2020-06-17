package com.sgcai.config.center.client.properties.spring;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sgcai.config.center.client.GlobalConfigration;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgcai.config.center.common.CommonConstant;
import com.sgcai.config.center.common.utils.ConfigCenterUtils;

public class AutoReloadPropertySourcesPlaceholderConfigurer extends AbstractPropertySourcesPlaceholderConfigurer {

    private static final Logger LOGGER=LoggerFactory.getLogger(AutoReloadPropertySourcesPlaceholderConfigurer.class);

    private static final ExecutorService POOL=Executors.newFixedThreadPool(2);

    private static final String DEFAULT_ZOOKEEPER_HOST=System.getenv(CommonConstant.CONFIG_CENTER_NOTIFY_ZOOKEEPER_HOST);

    private CuratorFramework client=null;

    private NodeCache nodeCache;

    protected String customZookeeperHostKey;

    public AutoReloadPropertySourcesPlaceholderConfigurer() throws IOException {
        super();
    }

    public void setCustomZookeeperHostKey(String customZookeeperHostKey) {
        this.customZookeeperHostKey = customZookeeperHostKey;
    }

    public void afterInit() throws Exception {

        if(customZookeeperHostKey!=null){
            String  customzookperhost=  System.getenv(customZookeeperHostKey);
            if (StringUtils.isBlank(customzookperhost)) {
                throw new IllegalArgumentException("miss system env： '" + customZookeeperHostKey+ "'");
            }
            createZookeeperClient(customzookperhost);
            watchProvider();
            return;
        }

        if (StringUtils.isBlank(DEFAULT_ZOOKEEPER_HOST)) {
            throw new IllegalArgumentException("miss system env： '" + CommonConstant.CONFIG_CENTER_NOTIFY_ZOOKEEPER_HOST + "'");
        }
        createZookeeperClient(DEFAULT_ZOOKEEPER_HOST);
        watchProvider();
    }

    public void destory() {
        if (client != null) {
            client.close();
        }
        if (nodeCache != null) {
            try {
                nodeCache.close();
            } catch(Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        if (POOL != null && !POOL.isShutdown()) {
            POOL.shutdownNow();
        }
    }

    private void createZookeeperClient(String zookeeperHost) throws Exception {
        RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000, 3);
        client=
            CuratorFrameworkFactory.builder().connectString(zookeeperHost)
                .namespace(CommonConstant.ZOOKEEPER_NAME_SPACE).sessionTimeoutMs(5000).connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy).build();

        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {

            public void stateChanged(CuratorFramework client, ConnectionState state) {
                String configVersion= GlobalConfigration.toString(CommonConstant.CONFIG_VERSION_CONFIG_KEY);
                try {
                    if (state.equals(ConnectionState.CONNECTED)) {
                        writeDate(propertiesName, configVersion);
                    } else if (state.equals(ConnectionState.RECONNECTED)) {
                        reloadProperties();
                        writeDate(propertiesName, configVersion);
                    }
                } catch(Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }

        }, POOL);

        client.start();
    }

    private void watchProvider() throws Exception {
        nodeCache=new NodeCache(client, ConfigCenterUtils.getProviderPath(propertiesName), false);
        nodeCache.getListenable().addListener(new NodeCacheListener() {

            public void nodeChanged() throws Exception {
                String newConfigVersion=new String(nodeCache.getCurrentData().getData());
                String configVersion=GlobalConfigration.toString(CommonConstant.CONFIG_VERSION_CONFIG_KEY);
                if (!newConfigVersion.equals(configVersion)) {
                    reloadProperties();
                    writeDate(propertiesName, newConfigVersion);
                }
            }
        }, POOL);
        nodeCache.start(true);
    }

    private void writeDate(String moduleEnName, String configVersion) throws Exception {
        String path=ConfigCenterUtils.getConsumerPath(moduleEnName, application);
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath(path, configVersion.getBytes());
        } catch(Exception e) {
            try {
                client.setData().forPath(ConfigCenterUtils.getConsumerPath(moduleEnName, application),
                    configVersion.getBytes());
            } catch(Exception e1) {
                LOGGER.error(e.getMessage(), e1);
            }
        }

    }
}
