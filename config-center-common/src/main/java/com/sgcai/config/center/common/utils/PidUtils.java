package com.sgcai.config.center.common.utils;

import java.lang.management.ManagementFactory;

public abstract class PidUtils {

    public static Integer getPid() {
        String name=ManagementFactory.getRuntimeMXBean().getName();
        String pid=name.split("@")[0];
        return Integer.valueOf(pid);
    }
}
