package org.microservicesboot.cloudclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"org.microservicesboot.cloudclient.services","org.microservicesboot.cloudclient1"})
@EnableHystrix
@ComponentScan({"org.microservicesboot.cloudclient.servicefacade","org.microservicesboot.cloudclient1"})
public class CloudClient1Application {
	

	public static void main(String[] args) {
		SpringApplication.run(CloudClient1Application.class, args);
	}
	
/*	
	 ///@Autowired DiscoveryClient client;
		//  This "LoadBalanced" RestTemplate 
		  //  is automatically hooked into Ribbon:
		  @Bean @LoadBalanced
		  RestTemplate restTemplate() {
		      return new RestTemplate();
		  }  
	*/	  

}
