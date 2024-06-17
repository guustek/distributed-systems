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
        System.out.printf("Request: %s %s%n", request.getMethod(), request.getRequestURL());
        System.out.printf("Request Host: %s%n", request.getRemoteHost());
        System.out.println();

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
