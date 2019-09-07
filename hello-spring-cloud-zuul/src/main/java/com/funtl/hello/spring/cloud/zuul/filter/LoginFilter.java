package com.funtl.hello.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;

@Component
public class LoginFilter   extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    /**过滤类型:
     * 返回值有：
     *pre: 路由器前
     * routing:路由时
     * post:路由后
     * error:发送错误调用
     * @return pre:路由之时
     */
    public String filterType() {
        return "pre";
    }


    /**过滤顺序:
     * @return 0 最先g过滤（其次是字符集过滤）
     */
    public int filterOrder() {//
        return 0;
    }

    /**是否过滤:
     * @return true 过滤
     */
    public boolean shouldFilter() {
        return true;
    }

    /**过滤的具体业务:
     * @return true 过滤
     */
    public Object run() throws ZuulException {
        //首先获取httpServletRequest请求对象然后从request请求对象中获取到登录信息

        RequestContext currentContext = RequestContext.getCurrentContext();

        HttpServletRequest request = currentContext.getRequest();
        logger.info("{} >>> {}", request.getMethod(), request.getRequestURL().toString());
        String username = request.getParameter("username");

        //登录信息为空则停止向下去访问zuul路由网关，返回登录提示信息
        if (username == null){
            System.out.println("username is empty");
            logger.warn("username is empty");
            //停止继续发送zuul响应请求不再继续往下访问路由
           currentContext.setSendZuulResponse(false);
           //设置返回响应码
           currentContext.setResponseStatusCode(401);

           //获取response响应请求对象发送先关错误提示信息
           try {
               //从zuul中获取HttpServletResponse
               HttpServletResponse response = currentContext.getResponse();
               //设置response响应类型和字符集
               response.setContentType("text/html;charset=utf-8");
               currentContext.getResponse().getWriter().write("非法的请求！");
           }catch(IOException e){
               e.printStackTrace();
            }
        }else {
            logger.info("login is OK");
        }

        return null;
    }
}
