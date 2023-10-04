package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
        /*
        15:58:37.335 [main] INFO hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic -- concreteLogic 실행
        */
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy); // 다형성에 의해서 ConcreteClient는 ConcreteLogic(부모)와 TimeProxy(자식) 모두 받을 수 있음
        client.execute();
        /*
        15:58:14.814 [main] INFO hello.proxy.pureproxy.concreteproxy.code.TimeProxy -- TimeProxy 실행
        15:58:14.816 [main] INFO hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic -- concreteLogic 실행
        15:58:14.816 [main] INFO hello.proxy.pureproxy.concreteproxy.code.TimeProxy -- TimeProxy 종료 resultTime=0
        * */
    }
}
