package com.slide5.Controller;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.slide5.Entity.User;
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
            // Redirect to login page if not logged in
            request.setAttribute("secureURL", httpRequest.getRequestURI());
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        } else {
            // Proceed with the request
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void destroy() {
        // Cleanup code if needed
    }

}
