package com.ibm.shopping.products.product.aop;

import com.ibm.shopping.products.product.aop.exception.NotFoundException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author DariusScridon
 * @created 07/12/2020 - 4:28 PM
 * @project shopping-product
 */

@Configuration
@Aspect
public class ExceptionLoggerPointCut {

    @AfterThrowing(pointcut = "execution(* com.ibm.shopping.products.product.*.*.*(..))", throwing = "exception")
    public void logError(NotFoundException exception){
        exception.printStackTrace();
    }
}
