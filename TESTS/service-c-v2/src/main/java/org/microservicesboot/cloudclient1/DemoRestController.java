package org.microservicesboot.cloudclient1;

import java.net.URI;
import java.util.List;

import org.microservicesboot.cloudclient.servicefacade.MicroserviceFacade;
import org.microservicesboot.cloudclient.services.ServiceA;
import org.microservicesboot.cloudclient.services.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DemoRestController {
	
	
	 @Autowired
	 MicroserviceFacade microServiceFacade;
	  
	  
	  

	  @GetMapping("/allprops")
	  public String showProps() {
		  
		  
		  
		  
		  String returnFinal="";
		  
		  returnFinal+="SERVICO - A : "+getServiceAResult()+"<br/>";
			 returnFinal+="SERVICO - B : "+getServiceBResult()+"<br/>";  
			  
			  return returnFinal;
		  
	  }
	  
	  
	  
	  public String getServiceAResult()
	  {
		  return microServiceFacade.getPropertyServiceA();
	  }
	  
	  
	 
	  public String getServiceBResult()
	  {
		  return microServiceFacade.getPropertyServiceB();
	  }
	 

	  
	  
	}


