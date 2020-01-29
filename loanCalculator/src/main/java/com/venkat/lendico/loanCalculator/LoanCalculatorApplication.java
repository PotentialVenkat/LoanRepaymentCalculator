package com.venkat.lendico.loanCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.venkat")
public class LoanCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanCalculatorApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				// .paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.venkat")).build().apiInfo(apiDetails());

	}

	private ApiInfo apiDetails() {
		Contact contact = new Contact("Venkat", "https://github.com/PotentialVenkat/", "Venkata.Yellaturi@gmail.com");
		return new ApiInfoBuilder().title("Lendico Loan repayment  documentation API").description("Calculator")
				.termsOfServiceUrl(" ").contact(contact).license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0").version("1.0.0").build();
	}

}
