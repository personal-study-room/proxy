package hello.proxy.cglib;


import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void concreteService() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); // CGLIB 만드는 클래스
        enhancer.setSuperclass(ConcreteService.class); // 슈퍼클래스를 임의로 생성 -> 구현체 주입
        enhancer.setCallback(new TimeMethodInterceptor(target)); // callback method 주입
        ConcreteService proxy = (ConcreteService) enhancer.create(); // proxy 인스턴스 생성
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
