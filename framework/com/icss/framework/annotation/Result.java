package com.icss.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Result注解
 * @author yyf
 *
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Result {
	String name();
	String value();
	ResultType type() default ResultType.forward;
}
