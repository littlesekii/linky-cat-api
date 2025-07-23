package cat.linky.linkycat_api.infra.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class RequestLoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("RequestLoggerFilter initialized!");
    }

    @Override
    public void doFilter(
        ServletRequest request, 
        ServletResponse response, 
        FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        System.out.println("\n================================");
        System.out.println(LocalDateTime.now().toString());
        System.out.println(
            "Incoming request: " + 
            httpRequest.getMethod() + 
            " " +
            httpRequest.getRequestURI()
        ); 

        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long elapsedTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Request completed in " + elapsedTime + "ms");    
    }

    @Override
    public void destroy() {
        System.out.println("RequestLoggerFilter destroyed!");
    }
}
