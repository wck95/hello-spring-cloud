package com.funtl.hello.spring.cloud.web.admin.feign.service.hystrix;

import com.funtl.hello.spring.cloud.web.admin.feign.service.IWebAdminFeignService;
import org.springframework.stereotype.Component;

@Component
public class AdminFeignHystrix  implements IWebAdminFeignService {

    @Override
    public String sayHi(String message) {
        return  "Hi , your message is :\""+ message + "\" but request error.";
    }
}
