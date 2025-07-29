package com.slide5;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        // Store application-level data in ServletContext
        context.setAttribute("appName", "Data Sharing Demo");
        context.setAttribute("appStartTime", System.currentTimeMillis());
        context.setAttribute("visitorCount", 0);
        System.out.println("✅ Application started - ServletContext initialized");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("❌ Application stopped - ServletContext destroyed");
    }
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer visitorCount = (Integer) context.getAttribute("visitorCount");
        context.setAttribute("visitorCount", visitorCount + 1);
        System.out.println("👤 New session created - Total visitors: " + (visitorCount + 1));
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("👋 Session destroyed");
    }
}
