package com.ecommerce.pricing.infrastructure.config;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ScheduleAspect {

  @Around("@annotation(com.ecommerce.pricing.infrastructure.config.TimedLog)")
  public Object executionJob(ProceedingJoinPoint point) throws Throwable {

    long start = System.currentTimeMillis();
    SimpleDateFormat formatTemps = new SimpleDateFormat("HH:mm:ss.SSS");

    log.info(
        "El metodo "
            + point.getSignature().getName()
            + " tiempo "
            + formatTemps.format(start)
            + " esta iniciando...");
    Object result = point.proceed();

    long end = System.currentTimeMillis();
    long betweenTime = System.currentTimeMillis() - start;
    long seconds = TimeUnit.MILLISECONDS.toMillis(betweenTime);

    log.info(
        point.getTarget().getClass()
            + "."
            + point.getSignature().getName()
            + " "
            + formatTemps.format(end)
            + " totalTime");

    log.info(
        "El metodo "
            + point.getSignature().getName()
            + " esta finalizando la ejecución in  "
            + seconds
            + " milliseconds");
    return result;
  }
}
