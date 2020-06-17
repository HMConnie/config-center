package com.sgcai.config.center.common.utils;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;

public class Dui1DuiStringUtils {

    /**
     * ID生成器
     * @return
     */
    public static String generateUUID() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    /**
     * 订单No生成器
     * @return
     */
    public static String generateOrderNo() {
        StringBuilder sb=new StringBuilder();
        String date=DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        sb.append(date);
        for (int i=0; i < 6; i++) {
            sb.append(RandomUtils.nextInt(10));
        }
        return sb.toString();
    }
}
