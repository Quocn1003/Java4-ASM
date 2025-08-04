package com.slide5.Controller;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.slide5.Entity.User;
@WebFilter(urlPatterns = {"/secure", "/demo", "/demo.jsp"}) // Apply this filter to secure URLs and demo page
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        // Check if user is logged in
        User user = (User) (session != null ? session.getAttribute("user") : null);

        if (user == null) {
            // Store the URL they were trying to access in session
            HttpSession newSession = httpRequest.getSession(true);
            String requestURI = httpRequest.getRequestURI();
            String queryString = httpRequest.getQueryString();
            String fullURL = requestURI + (queryString != null ? "?" + queryString : "");
            newSession.setAttribute("secureURL", fullURL);
            
            // Redirect to login page if not logged in
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        } else {
            // User is logged in, proceed with the request
            System.out.println("🔒 AuthFilter: User " + user.getEmail() + " accessing " + httpRequest.getRequestURI());
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void destroy() {
        // Cleanup code if needed
    }

}
