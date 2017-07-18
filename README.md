#### 在dubbo中对于消费者的保护提供了actives进行并发控制保护，但是功能相对薄弱，下面我们探讨下如何使用Netflix提供的服务容错组件Hystrix对dubo消费者提供线程隔离保护


### 引入
```
<dependency>
    <groupId>com.netease</groupId>
    <artifactId>dubbo-hystrix-support</artifactId>
    <version>0.0.1</version>
</dependency>
```

### 配置
```
@Bean
@Scope("prototype")
public HystrixConfig hystrixConfig(){
    HystrixConfig config = new HystrixConfig();
    config.setCommandPropertiesDefaults(HystrixCommandProperties.Setter()
            .withCircuitBreakerRequestVolumeThreshold(19)//10秒钟内至少19此请求失败，熔断器才发挥起作用
            .withCircuitBreakerSleepWindowInMilliseconds(30000)//熔断器中断请求30秒后会进入半打开状态,放部分流量过去重试
            .withCircuitBreakerErrorThresholdPercentage(50)//错误率达到50开启熔断保护
            .withExecutionTimeoutEnabled(false)//使用dubbo的超时，禁用这里的超时
    );
    config.setThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(30));//线程池为30
    return config;
}
```
