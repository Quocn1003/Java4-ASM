package com.slide5;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/demo")
public class DataSharingServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. HttpServletRequest - Request scope (single request only)
        request.setAttribute("requestData", "Data stored in request scope - " + System.currentTimeMillis());
        request.setAttribute("requestInfo", "This data exists only for this request");
        
        // 2. HttpSession - Session scope (across multiple requests from same user)
        HttpSession session = request.getSession();
        Integer pageViews = (Integer) session.getAttribute("pageViews");
        if (pageViews == null) {
            pageViews = 1;
        } else {
            pageViews++;
        }
        session.setAttribute("pageViews", pageViews);
        session.setAttribute("sessionData", "Hello from session! You've visited " + pageViews + " times");
        
        // 3. ServletContext - Application scope (shared across all users)
        // Application data was set in AppListener and will be accessed in JSP
        
        // Forward to JSP to demonstrate PageContext
        RequestDispatcher dispatcher = request.getRequestDispatcher("/demo.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Handle form submission and update session data
        String userName = request.getParameter("userName");
        if (userName != null && !userName.trim().isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", userName);
        }
        
        // Redirect to avoid resubmission
        response.sendRedirect("demo");
    }
}
