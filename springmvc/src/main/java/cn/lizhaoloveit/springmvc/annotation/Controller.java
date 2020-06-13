package cn.lizhaoloveit.springmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//表示该注解是加在什么位置
@Target({ ElementType.TYPE })
//表示该注解是作用于哪个阶段（编译阶段、保存到class文件、运行阶段）
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
	// @Controller(value="")
	String value() default "";
}
