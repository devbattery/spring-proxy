package hello.proxy.pureproxy;

import hello.proxy.pureproxy.code.CacheProxy;
import hello.proxy.pureproxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.code.RealSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @DisplayName("no Proxy Test")
    @Test
    void noProxyTest() {
        // given
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);

        // when
        // then
        client.execute();
        client.execute();
        client.execute();
    }

    @DisplayName("cache Proxy Test")
    @Test
    void cacheProxyTest() {
        // given
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        // when
        // then
        client.execute();
        client.execute();
        client.execute();
    }

}
