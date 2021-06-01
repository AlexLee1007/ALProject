package com.alexe.www.aop

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
 * @package: com.alexe.www.aop
 * @author: Alex Lee
 * @date: 2021/6/1 10:07
 * @des: <b>此处描述当前类</b>
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SingleClick(val value: Long = 1000)