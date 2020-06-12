package com.usersapi.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2WebMvc
@EnableWebMvc
public class SpringFoxConfiguration implements WebMvcConfigurer {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.usersapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(customInfo());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo customInfo() {

        ApiInfo customInfo = new ApiInfo(
                "Users API",
                "CodeFiction Project",
                "2.0",
                "Terms of service",
                new Contact("Gabriel Pulga", "www.codefiction.net", "gabrieelplg@gmail.com"),
                "API license",
                "API license URL",
                Collections.emptyList());

        return customInfo;
    }
}