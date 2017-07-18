package com.netease.hystrix.dubbo.rpc.filter;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * Created by shendebin on 2017/7/17.
 */
public class HystrixConfig {
    private HystrixCommandProperties.Setter commandPropertiesDefaults;

    private HystrixThreadPoolProperties.Setter threadPoolPropertiesDefaults;

    public HystrixCommandProperties.Setter getCommandPropertiesDefaults() {
        return commandPropertiesDefaults;
    }

    public void setCommandPropertiesDefaults(HystrixCommandProperties.Setter commandPropertiesDefaults) {
        this.commandPropertiesDefaults = commandPropertiesDefaults;
    }

    public HystrixThreadPoolProperties.Setter getThreadPoolPropertiesDefaults() {
        return threadPoolPropertiesDefaults;
    }

    public void setThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter threadPoolPropertiesDefaults) {
        this.threadPoolPropertiesDefaults = threadPoolPropertiesDefaults;
    }
}
