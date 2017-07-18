package com.netease.hystrix.dubbo.rpc.filter;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public class DubboHystrixCommand extends HystrixCommand<Result> {

    private Invoker<?> invoker;
    private Invocation invocation;

    DubboHystrixCommand(Invoker<?> invoker, Invocation invocation, HystrixConfig config){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(invoker.getInterface().getName()))
                .andCommandKey(
                        HystrixCommandKey.Factory.asKey(
                                String.format("%s_%d", invocation.getMethodName(), invocation.getArguments() == null ? 0 : invocation.getArguments().length)
                        )
                )
                .andCommandPropertiesDefaults(config.getCommandPropertiesDefaults())
                .andThreadPoolPropertiesDefaults(config.getThreadPoolPropertiesDefaults())
        );
        this.invoker = invoker;
        this.invocation = invocation;
    }

    @Override
    protected Result run() throws Exception {
        Result result = invoker.invoke(invocation);
        if(result.hasException()){
            throw new RuntimeException(result.getException());
        }
        return result;
    }

    @Override
    protected Result getFallback() {
        return super.getFallback();
    }
}
