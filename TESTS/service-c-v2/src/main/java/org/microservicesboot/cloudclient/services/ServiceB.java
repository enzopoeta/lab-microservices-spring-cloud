package org.microservicesboot.cloudclient.services;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@FeignClient("serviceb")
public interface ServiceB {
	
	@RequestMapping(method=RequestMethod.GET,value="/")
	public String getProperties();

}
