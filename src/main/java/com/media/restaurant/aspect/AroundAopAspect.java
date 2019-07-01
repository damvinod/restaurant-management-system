package com.media.restaurant.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.media.restaurant.exception.ExceptionConfigProperties;
import com.media.restaurant.exception.GlobalException;

@Aspect
@Configuration
public class AroundAopAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(AroundAopAspect.class);

  @Autowired
  private ExceptionConfigProperties exceptionConfigProperties;

  @Around("com.media.restaurant.aspect.JoinPointConfig.controllerLayerExecution()")
  public Object allControllerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

    LOGGER.info("Begining of the Controller {} with the method {}",
        joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

    Object obj = null;

    try {
      obj = joinPoint.proceed();
    } catch (Exception exe) {

      Function<Entry<String, String>, List<String>> exceptionCreation =
          (Entry<String, String> entry) -> {
            List<String> exceptionDetailsList = new ArrayList<>();
            String[] errorCode = entry.getKey().split("_");

            exceptionDetailsList.add(errorCode[0] + "_" + errorCode[1]);
            exceptionDetailsList.add(entry.getValue());

            return exceptionDetailsList;
          };

          List<String> exceptionDetailsList =
              exceptionConfigProperties.getExceptionDetailsMap().entrySet().stream()
              .filter(entry -> entry.getKey().contains(joinPoint.getSignature().getName()))
              .map(exceptionCreation).collect(Collectors.toList()).get(0);

          LOGGER.error("exception in {} with error code {}",
              joinPoint.getSignature().getName(), exceptionDetailsList.get(0), exe);

          throw new GlobalException(exceptionDetailsList.get(0), exceptionDetailsList.get(1));
    }

    LOGGER.info("End of the Controller {} with the method {}",
        joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

    return obj;
  }

  @Around("com.media.restaurant.aspect.JoinPointConfig.serviceLayerExecution()")
  public Object allServiceAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

    LOGGER.info("Begining of the Service {} with the method {}",
        joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

    Object obj = joinPoint.proceed();

    LOGGER.info("End of the Service {} with the method {}",
        joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

    return obj;
  }

}
