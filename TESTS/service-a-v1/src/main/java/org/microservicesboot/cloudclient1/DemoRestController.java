package org.microservicesboot.cloudclient1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
	
	

	  @Value("${propriedade1}") String propriedade1;
	  @Value("${propriedade2}") String propriedade2;

	  @GetMapping("/")
	  public @ResponseBody String showProps() {
	    return "Propriedades vindas do Cloud Config Server : - propriedade1 --> " + propriedade1 +"; -propriedade 2 --> "+propriedade2;
	  }
	}


