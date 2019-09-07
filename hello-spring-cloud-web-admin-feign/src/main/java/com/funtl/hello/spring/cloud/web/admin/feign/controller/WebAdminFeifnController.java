package com.funtl.hello.spring.cloud.web.admin.feign.controller;

import com.funtl.hello.spring.cloud.web.admin.feign.service.IWebAdminFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebAdminFeifnController {

    @Autowired
    private IWebAdminFeignService webAdminFeignService;

    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public  String feignSayHi(@RequestParam("message") String message){
        return webAdminFeignService.sayHi(message);
    }
}
