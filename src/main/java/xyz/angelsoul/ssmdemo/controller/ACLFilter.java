package xyz.angelsoul.ssmdemo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ACLFilter implements Filter {
    private ServletContext sc;
    private ApplicationContext ctx;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sc = filterConfig.getServletContext();
        //获取Spring容器
        ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();
        
        System.out.println(path);
//        path = path.substring(path.lastIndexOf("/"));
//        System.out.println(path);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
