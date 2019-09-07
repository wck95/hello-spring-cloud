package com.funlt.hello.spring.cloud.web.admin.ribbon.controller;

import com.funlt.hello.spring.cloud.web.admin.ribbon.services.WebAdminRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebAdminRibbonController {

    @Autowired
    WebAdminRibbonService adminService;

    @RequestMapping(value="hi",method= RequestMethod.GET)
    public  String getmMssage (@RequestParam(value = "message")String message){
        System.out.println("message="+message);
        return adminService.sayHi(message);
    }

}
