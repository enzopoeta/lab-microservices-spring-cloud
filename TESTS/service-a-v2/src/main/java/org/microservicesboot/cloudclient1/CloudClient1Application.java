package org.microservicesboot.cloudclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(CloudClient1Application.class, args);
	}
}
