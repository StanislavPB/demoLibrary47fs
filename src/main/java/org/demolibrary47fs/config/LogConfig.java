package org.demolibrary47fs.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
@Aspect
public class LogConfig {

    @Pointcut("execution (public * org.demolibrary47fs.controller.*.*(..))")
    public void lofForController() {}


    @Pointcut("execution (public * org.demolibrary47fs.service.*.*(..))")
    public void lofForService() {}


    @Before("lofForController()")
    public void beforeUseinfAnyController(JoinPoint point){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert attributes != null;

        HttpServletRequest request = attributes.getRequest();

        log.info(
                """
                        RECEIVED REQUEST:
                        IP:{}
                        HTTP METHOD: {}
                        URL: {}
                        """,
                request.getRemoteAddr(),
                request.getMethod(),
                request.getRequestURL().toString()
        );
    }

    @Before("lofForService()")
    public void beforeUseinfAnyService(JoinPoint point){
       log.info("\n RUN SERVICE: \n SERVICE : {}.{} ",
               point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
    }
}
