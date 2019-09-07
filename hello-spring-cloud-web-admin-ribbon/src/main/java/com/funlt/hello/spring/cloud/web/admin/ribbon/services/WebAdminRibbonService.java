package com.funlt.hello.spring.cloud.web.admin.ribbon.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebAdminRibbonService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHi(String message){
        System.out.println("message="+message);
        return restTemplate.getForObject("http://hello-spring-cloud-service-admin/hi/?message=" + message,String.class);
    }

    public String ShowError(String message){
        System.out.println("message="+message);
        return "Hi , your message is :\""+ message + "\" but request error.";
    }

    public String hiError(String message) {
        return "Hi，your message is :\"" + message + "\" but request error.";
    }
}
