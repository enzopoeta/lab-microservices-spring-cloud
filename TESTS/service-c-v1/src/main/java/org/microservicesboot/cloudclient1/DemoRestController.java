package org.microservicesboot.cloudclient1;

import java.net.URI;
import java.util.List;

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

@RestController
public class DemoRestController {
	
	
	
	  @Autowired
	  RestTemplate restTemplate;
	  
	  //@Autowired
	  //  private LoadBalancerClient loadBalancer;
	  
	  

	  @GetMapping("/allprops")
	  public String showProps() {
		  
		  
		  
		  
		  String returnFinal="";
		  
		 returnFinal+="SERVICO - A : "+getServiceAResult()+"<br/>";
		 returnFinal+="SERVICO - B : "+getServiceBResult()+"<br/>";  
		  
		  return returnFinal;

	  }

	  public String getServiceAResult()
	  {
		  return getClientProps("servicea");
	  }
	  
	  public String getServiceBResult()
	  {
		  return getClientProps("serviceb");
	  }
	  
	  
	  
	  public String getClientProps(String service) {
		  
		/*  
		  ServiceInstance instance = loadBalancer.choose("cloud-client1");
	        URI uri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
	        return (new RestTemplate()).getForObject(uri,String.class);
	        */
		  return restTemplate.getForObject("http://" + service, String.class);
		  
   
		  
		  /*
		  List<ServiceInstance> list = client.getInstances(service);
		   
		    if (list != null && list.size() > 0 ) {
		    	 for (ServiceInstance serviceInstance : list) {
				    	System.out.println(serviceInstance.getUri().toString());
					}
		      URI uri = list.get(0).getUri();
		  if (uri !=null ) {
		    return (new RestTemplate()).getForObject(uri,String.class);
		  }
		    }
		    return null; */
		  }
	  
	  
	}


