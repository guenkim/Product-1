package com.study.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.study.contents"))
                .paths(PathSelectors.ant("/api/**"))
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        String description = "Next Man의 API 문서 입니다.";
        return new ApiInfoBuilder()
                .title("Next Man's Api.")
                .description(description)
                .version("1.0")
                .build();
    }
}