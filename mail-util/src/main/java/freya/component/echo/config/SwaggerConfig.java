package freya.component.echo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author HQ
 * @Description Swagger配置类
 * @Date 2019/5/17 16:11
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${info.app.version}")
    String apiVersion;
    @Value("${info.app.name}")
    String appName;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("freya.email.echo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @return
     * @description swagger的基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(appName)
                .version(apiVersion)
                .build();
    }
}