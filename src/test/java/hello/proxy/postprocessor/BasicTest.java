package hello.proxy.postprocessor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {
    
    @DisplayName("basic config test")
    @Test
    void basicConfig() {
        // given
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        // A는 빈으로 등록된다.
        A a = applicationContext.getBean("beanA", A.class);
        a.helloA();

        // B는 빈으로 등록되지 않는다.
        assertThatThrownBy(() -> applicationContext.getBean(B.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Slf4j
    @Configuration
    static class BasicConfig {

        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

    }

    @Slf4j
    static class A {

        public void helloA() {
            log.info("hello A");
        }

    }

    @Slf4j
    static class B {

        public void helloB() {
            log.info("hello B");
        }

    }

}
