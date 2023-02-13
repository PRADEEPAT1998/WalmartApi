package com.wallmart;

import java.io.ObjectStreamException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.wallmart.service.ProductService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class WalmartApplication {

	public static void main(String[] args) {
		// SpringApplication.run(WalmartApplication.class, args);

		ApplicationContext applicationContext = SpringApplication.run(WalmartApplication.class, args);
		ProductService productService = applicationContext.getBean(ProductService.class);
		try {
			productService.getProducts();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ObjectStreamException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} 
	}

}
