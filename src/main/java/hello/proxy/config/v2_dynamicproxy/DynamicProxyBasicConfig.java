package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicProxyBasicConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderControllerV1, logTrace, PATTERNS));
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderServiceV1, logTrace, PATTERNS));
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();
        return (OrderRepositoryV1) Proxy.newProxyInstance(
                OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepositoryV1, logTrace, PATTERNS));
    }

}
