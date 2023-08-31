package com.koorung.kotlinplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.function.router

@SpringBootApplication
class KotlinPlaygroundApplication

fun main(args: Array<String>) {

    // 코틀린에서 BeanDefinitionDsl을 통해 @Configuration의 기능을 바로 사용할 수 있다.
    val corsBeans = beans {
        bean {
            object : WebMvcConfigurer {
                override fun addCorsMappings(registry: CorsRegistry) {
                    registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                }
            }
        }
    }

    // addInitializers() 을 통해 스프링부트 앱 실행 전 초기화를 할 수 있다.
    runApplication<KotlinPlaygroundApplication>(*args) {
        addInitializers(corsBeans)
    }
}
