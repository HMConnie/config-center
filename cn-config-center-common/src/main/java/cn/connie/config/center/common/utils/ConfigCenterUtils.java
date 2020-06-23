package cn.connie.config.center.common.utils;

import java.net.URLEncoder;

import cn.connie.config.center.common.CommonConstant;

public abstract class ConfigCenterUtils {

    public static String getProviderPath(String moduleEnName) {
        StringBuilder sb=new StringBuilder();
        sb.append("/");
        sb.append(CommonConstant.PROVIDER_KEY).append("/").append(moduleEnName);
        return sb.toString();
    }

    public static String getConsumerPath(String moduleEnName, String application) throws Exception {
        StringBuilder sb=new StringBuilder();
        sb.append(getConsumerMainPath(moduleEnName));
        sb.append("/").append(getURL(application));
        return sb.toString();
    }

    public static String getConsumerPathByMainPath(String mainPath, String childPath) throws Exception {
        StringBuilder sb=new StringBuilder();
        sb.append(mainPath);
        sb.append("/").append(childPath);
        return sb.toString();
    }

    public static String getConsumerMainPath(String moduleEnName) {
        StringBuilder sb=new StringBuilder();
        sb.append("/");
        sb.append(CommonConstant.CONSUMER_KEY).append("/").append(moduleEnName);
        return sb.toString();
    }

    public static String getURL(String application) throws Exception {
        String localhost=NetUtils.getLocalHost();
        Integer pid=PidUtils.getPid();
        StringBuilder sb=new StringBuilder();
        sb.append(CommonConstant.AUTO_RELOAD_PROTOCOL).append("://");
        sb.append(localhost).append(":").append(pid).append("/").append(application);
        return URLEncoder.encode(sb.toString(), "UTF-8");
    }
}
