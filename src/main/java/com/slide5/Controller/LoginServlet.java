package com.slide5.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slide5.DAO.UserDAO;
import com.slide5.Entity.User;
import com.slide5.Service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
        // Forward to login page
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserService(); // Assuming UserService implements UserDAO
        User user = userDAO.findByEmail(email);

        if(user == null) {
            request.setAttribute("message", "Email not found");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else if (!user.getPassword().equals(password)) {
            request.setAttribute("message", "Incorrect password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // Login successful - store user in session and redirect to index
            request.getSession().setAttribute("user", user);
            
            // Check if there was a secure URL they were trying to access
            String secureURL = (String) request.getSession().getAttribute("secureURL");
            if (secureURL != null) {
                request.getSession().removeAttribute("secureURL");
                response.sendRedirect(secureURL);
            } else {
                // Redirect to index.html which will auto-redirect to demo
                response.sendRedirect(request.getContextPath() + "/index.html");
            }
        }
    }

}
