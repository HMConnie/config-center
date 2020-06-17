package com.sgcai.config.center.notify;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import com.sgcai.config.center.entity.Consumer;
import com.sgcai.config.center.entity.URL;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgcai.config.center.common.CommonConstant;
import com.sgcai.config.center.common.utils.ConfigCenterUtils;

public class ZookeeperNotifier implements Notifier {

    private static final Logger LOGGER=LoggerFactory.getLogger(ZookeeperNotifier.class);

    private static final Object LOCK=new Object();

    private CuratorFramework client=null;

    private String zookeeperHost=null;

    public void init() throws Exception {
        createZookeeperClient();
    }

    public void setZookeeperHost(String zookeeperHost) {
        this.zookeeperHost=zookeeperHost;
    }

    public void destory() {
        client.close();
    }

    public void notify(String moduleEnName, String configVersion) throws Exception {
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath(ConfigCenterUtils.getProviderPath(moduleEnName), configVersion.getBytes());
        } catch(Exception e) {
            try {
                client.setData().forPath(ConfigCenterUtils.getProviderPath(moduleEnName), configVersion.getBytes());
            } catch(Exception e1) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private void createZookeeperClient() throws Exception {
        if (client == null) {
            synchronized(LOCK) {
                if (client == null) {
                    client=
                        CuratorFrameworkFactory.builder().connectString(zookeeperHost)
                            .namespace(CommonConstant.ZOOKEEPER_NAME_SPACE).sessionTimeoutMs(5000)
                            .connectionTimeoutMs(3000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
                    client.start();

                }
            }
        }
    }

    public List<Consumer> getAllConsumer(String moduleEnName) {
        List<Consumer> consumers=new ArrayList<Consumer>();
        try {
            String mainPath=ConfigCenterUtils.getConsumerMainPath(moduleEnName);
            List<String> list=client.getChildren().forPath(mainPath);
            for (String childPath: list) {
                consumers.add(genConsumer(mainPath, childPath));
            }
        } catch(Exception e) {

        }
        return consumers;
    }

    private Consumer genConsumer(String mainPath, String childPath) throws Exception {
        String node=URLDecoder.decode(childPath, "UTF-8");
        String value=
            new String(client.getData().forPath(ConfigCenterUtils.getConsumerPathByMainPath(mainPath, childPath)));
        URL url=new URL(node);
        Consumer consumer=new Consumer();
        consumer.setApplication(url.getPath());
        consumer.setHost(url.getHost());
        consumer.setPid(url.getPort());
        consumer.setConfigVersion(value);
        return consumer;
    }
}
