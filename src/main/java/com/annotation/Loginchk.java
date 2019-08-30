package com.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Loginchk {

	public enum Role{USER,MANAGER,ADMIN};
	
	public Role role() default Role.USER;
}
