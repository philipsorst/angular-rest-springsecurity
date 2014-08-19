package net.dontdrinkandroot.example.angularrestspringsecurity.aop;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoAroundMethod implements MethodInterceptor {
	private static final Logger LOG = LoggerFactory.getLogger(DoAroundMethod.class);
	
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
 
		LOG.info("****SPRING AOP**** DoAroundMethod: Method name : "
				+ methodInvocation.getMethod().getName());
		
		LOG.info("****SPRING AOP**** DoAroundMethod: Method name : "
				+ methodInvocation.getMethod().getName());
		LOG.info("****SPRING AOP**** DoAroundMethod: Method arguments : "
				+ Arrays.toString(methodInvocation.getArguments()));
		// same with MethodBeforeAdvice
		LOG.info("****SPRING AOP**** DoAroundMethod: Before method executing!");
 
		try {
			// proceed to original method call
			Object result = methodInvocation.proceed();
			// same with AfterReturningAdvice
			LOG.info("****SPRING AOP**** DoAroundMethod: After method executing!");
			return result;
 
		} catch (IllegalArgumentException e) {
			// same with ThrowsAdvice
			LOG.info("****SPRING AOP**** DoAroundMethod: When method throws Exception!");
			throw e;
		}
	}

}