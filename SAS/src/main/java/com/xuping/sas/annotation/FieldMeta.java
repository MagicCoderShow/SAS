package com.xuping.sas.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义注解
 * @author wuxuping
 *
 */
@Retention(RetentionPolicy.RUNTIME)// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.FIELD,ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface FieldMeta {
	/** 
     * 是否为序列号 
     * @return 
     */  
    boolean id() default false;  
    /** 
     * 字段名称 
     * @return 
     */  
    String name() default "";  
    /** 
     * 是否可编辑 
     * @return 
     */  
    boolean editable() default true;  
    /** 
     * 是否在列表中显示 
     * @return 
     */  
    boolean summary() default true;  
    /** 
     * 字段描述 
     * @return 
     */  
    String description() default "";  
    /** 
     * 排序字段 
     * @return 
     */  
    int order() default 1;
    /**
     * 是否状态列（男，女，是，否）
     * @return
     */
    boolean whether() default false;
    /**
     * 状态列字段名，参考SubKey.Class
     * {@inheritDoc new SubKey()}
     * @return
     */
    String whethervalue() default "";
    /**
     * 是否加密
     * 加密方式MD5
     * @return
     */
    boolean encrypt() default false; 
    /**
     * 是否外键
     * @return
     */
    boolean iskey() default false;
    /**
     * 外键表名（iskey==true时有效）
     * @return
     */
    String keytable() default "";
    /**
     * 外键字段（iskey==true,并且keytable!=null时有效，默认为id）
     * @return
     */
    String keyname() default "id";
    /**
     * 外键展示字段名称，默认name
     * @return
     */
    String keyvalue() default "name";
    /**
     * 是否导入
     * @return
     */
    boolean isimport() default true;
    /**
     * 是否图片
     */
    boolean isimg() default false;
}
