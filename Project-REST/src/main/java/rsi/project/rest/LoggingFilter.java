package rsi.project.rest;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        System.out.println();
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Request Host: " + request.getRemoteHost());
        System.out.println();

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
