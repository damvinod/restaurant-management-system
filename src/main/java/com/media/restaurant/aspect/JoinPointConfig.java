package com.media.restaurant.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class JoinPointConfig {

  /* @Pointcut("controllerLayerExecution() || serviceLayerExecution()")
  public void allLayerExecution(){}*/

  @Pointcut("execution(* com.media.restaurant.controller.*.*(..))")
  public void controllerLayerExecution(){}

  @Pointcut("execution(* com.media.restaurant.service.*.*(..))")
  public void serviceLayerExecution(){}

}
