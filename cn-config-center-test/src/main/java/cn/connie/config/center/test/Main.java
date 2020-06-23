package cn.connie.config.center.test;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

@ComponentScan(basePackages={"com.xiaoyuan.config.center.test"})
@ContextConfiguration
@ImportResource({"classpath:applicationContext.xml"})
public class Main {

    private static final Logger LOGGER=LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
        LOGGER.info("cashier core service start");
        CountDownLatch latch=new CountDownLatch(1);
        latch.await();
    }
}
