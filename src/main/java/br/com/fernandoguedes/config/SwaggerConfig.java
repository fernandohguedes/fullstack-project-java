package br.com.fernandoguedes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile(value = {"dev"})
@EnableSwagger2
public class SwaggerConfig {

    @Value("${info.build.version}")
    private String version;

    private String FULLSTACK_PROJECT = "FULLSTACK PROJECT";
    private String CLIENTE = "Clientes";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.fernandoguedes.app.controllers"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfoBookStore())
                .tags(new Tag(CLIENTE, "Recursos de Cliente"));
    }

    private ApiInfo apiInfoBookStore() {
        return new ApiInfoBuilder().title(FULLSTACK_PROJECT)
                .description("Documentação da API - " + FULLSTACK_PROJECT)
                .version(version).build();
    }

    @Bean
    public UiConfiguration tryItOutConfig() {
        final String[] methodsWithTryItOutButton = {};
        return UiConfigurationBuilder.builder().supportedSubmitMethods(methodsWithTryItOutButton).build();
    }
}
