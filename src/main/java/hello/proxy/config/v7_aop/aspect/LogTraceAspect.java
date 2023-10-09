package hello.proxy.config.v7_aop.aspect;


import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect   // advisor 를 편리하게 생성.
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Around("execution(* hello.proxy.app..*(..))") // pointcut
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{ // 메서드는 advice
        TraceStatus status = null;
        try {
            /*

            // 타겟 인스턴스
            log.info("target={}", joinPoint.getTarget());
            // 메서드의 인자
            log.info("getArgs={}", joinPoint.getArgs());
            // 메서드의 시그니처
            log.info("getSigniture{}", joinPoint.getSignature().toString());

            */


            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            // target 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
