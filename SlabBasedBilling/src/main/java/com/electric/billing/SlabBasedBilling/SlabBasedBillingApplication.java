package com.electric.billing.SlabBasedBilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.electric.billing.SlabBasedBilling.*")
public class SlabBasedBillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlabBasedBillingApplication.class, args);
	}

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()).select().build();
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Slab Based Billing")
				.description("Check you electricity bill by clicking try now button")
				.version("2.0").build();
	}
}
