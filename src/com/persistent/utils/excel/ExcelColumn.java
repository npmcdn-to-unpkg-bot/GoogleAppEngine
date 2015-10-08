package com.persistent.utils.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Reference: http://persistentdesigns.com/wp/?p=512
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.METHOD)
public @interface ExcelColumn {
	boolean ignore() default false;
	String label() default "";
}
