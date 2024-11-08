package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

    @DisplayName("reflection test 0")
    @Test
    void reflection0() {
        // given
        Hello target = new Hello();

        // when
        // 공통 로직 1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);

        // 공통 로직 2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);

        // then
    }

    @DisplayName("reflection test 1")
    @Test
    void reflection1() throws Exception {
        // given
        // 클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        // when
        // then
        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        // callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {

        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }

    }

}
