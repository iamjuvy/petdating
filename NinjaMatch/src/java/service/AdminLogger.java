/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.Transactional;
import javax.validation.groups.Default;
import org.jboss.logging.LoggingClass;

/**
 *
 * @author yyun
 */

@Interceptor
public class AdminLogger {
    
    @AroundInvoke
    private Object logMethod(InvocationContext ic) throws Exception{
        //logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        System.out.println("Interceptor start: " + ic.getTarget().toString() + "_" + ic.getMethod().getName());
        try{
            return ic.proceed();
        }finally{
            System.out.println("Interceptor end: " + ic.getTarget().toString() + "_" + ic.getMethod().getName());
            //logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
        }
    }
}
