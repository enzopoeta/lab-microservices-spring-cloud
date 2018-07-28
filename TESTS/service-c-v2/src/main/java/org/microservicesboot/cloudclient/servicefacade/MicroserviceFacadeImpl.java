package org.microservicesboot.cloudclient.servicefacade;

import org.microservicesboot.cloudclient.services.ServiceA;
import org.microservicesboot.cloudclient.services.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MicroserviceFacadeImpl implements MicroserviceFacade {

	@Autowired
	private ServiceA serviceA;

	@Autowired
	private ServiceB serviceB;

	@Override
	@HystrixCommand(fallbackMethod = "getServiceAResultFallback")
	public String getPropertyServiceA() {
		return serviceA.getProperties();
	}

	@Override
	@HystrixCommand(fallbackMethod = "getServiceBResultFallback")
	public String getPropertyServiceB() {
		return serviceB.getProperties();
	}

	public String getServiceBResultFallback() {
		return "FALLBACK PROPERTIES FOR SERVICE B";
	}

	public String getServiceAResultFallback() {
		return "FALLBACK PROPERTIES FOR SERVICE A";
	}

}
