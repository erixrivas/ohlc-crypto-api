package com.example.ohlccryptoapi.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpMethod;

import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiInfo apiInfo() {
        return new ApiInfo("OHLC",
                "Some custom description of API.",
                "1.0",
                "Terms of service",
                new Contact("ERIX RIVAS", "", ""),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}


//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
////    @Bean
////    public Docket api() {
////        return new Docket(DocumentationType.SWAGGER_2).select()
////                .apis(RequestHandlerSelectors.basePackage("com.example.ohlccryptoapi.api"))
////                .paths(PathSelectors.ant("/foos/*"))
////                .build()
////                .apiInfo(apiInfo())
////                .useDefaultResponseMessages(false)
////                .globalResponses(HttpMethod.GET, Arrays.asList(
////                        new ResponseBuilder().code("500")
////                                .description("500 message").build(),
////                        new ResponseBuilder().code("403")
////                                .description("Forbidden!!!!!").build()
////                ));
////    }
//
//    private ApiInfo apiInfo() {
//        ApiInfo apiInfo = new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service", new Contact("John Doe", "www.example.com", "myeaddress@company.com"), "License of API", "API license URL", Collections.emptyList());
//        return apiInfo;
//    }