package com.mel0ny.springboot.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    /**
     * 过滤器的核心方法，对每个HTTP请求进行处理
     * @param req 请求对象
     * @param res 响应对象
     * @param chain 过滤器链，用于传递请求
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        // 设置允许跨域访问的源地址
        // http://localhost:5173是Vue开发服务器的默认地址
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");

        // 设置允许的HTTP请求方法
        // 包括GET(查询), POST(新增), PUT(修改), DELETE(删除), OPTIONS(预检请求)
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // 设置允许的请求头字段
        // Content-Type: 请求体类型, Authorization: 认证信息, X-Requested-With: 标识Ajax请求
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        // 设置是否允许携带凭证信息(如Cookie)
        // true表示前端可以发送带有凭证的请求
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // 设置预检请求的缓存时间(秒)
        // 3600秒=1小时，浏览器在此期间内不会重复发送预检请求
        response.setHeader("Access-Control-Max-Age", "3600");

        // 处理预检请求(OPTIONS请求)
        // 浏览器在实际发送复杂请求前，会先发送OPTIONS请求进行"预检"
        // 如果预检请求不通过，浏览器会阻止实际请求的发送
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // 预检请求直接返回200状态码，表示服务器允许跨域
            response.setStatus(HttpServletResponse.SC_OK);
            return;  // 预检请求处理完毕，不再继续执行过滤器链
        }

        // 非预检请求(如GET/POST等)继续执行过滤器链
        // 这会调用下一个过滤器或最终的Controller方法
        chain.doFilter(req, res);
    }
}