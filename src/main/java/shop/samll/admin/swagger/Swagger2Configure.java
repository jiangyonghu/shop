package shop.samll.admin.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configure {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //swagger要扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("shop.samll.admin.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("小控交易平台API")
                .description("请求头：" +
                        "X-Shop-Admin-Token:TOKEN\n" +
                        "accept:application/json, text/plain, */*\n" +
                        "content-type:application/json;charset=UTF-8")
                .termsOfServiceUrl("https://www.baidu.com/")
                .contact(new Contact("百度","https://www.baidu.com/","baidu@qq.com"))
                .version("1.0")
                .build();
    }


}
