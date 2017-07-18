package com.netease.hystrix.dubbo.rpc.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Activate(group = Constants.CONSUMER, order = 90000)
public class HystrixFilter implements Filter, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        DubboHystrixCommand command = new DubboHystrixCommand(invoker, invocation, getConfig());
        return command.execute();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private HystrixConfig getConfig(){
        return this.applicationContext.getBean("hystrixConfig", HystrixConfig.class);
    }
}
