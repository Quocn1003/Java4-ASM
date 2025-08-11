package com.slide5.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
        // Nếu đã login rồi thì redirect về home luôn
        if (request.getSession(false) != null && request.getSession(false).getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    request.setAttribute("savedUsername", cookie.getValue());
                } else if ("password".equals(cookie.getName())) {
                    request.setAttribute("savedPassword", cookie.getValue());
                }
            }
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nếu đã login rồi thì redirect về home luôn
        if (request.getSession(false) != null && request.getSession(false).getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        // Phần xử lý login như cũ
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        UserDAO userDAO = new UserService();
        User user = userDAO.findById(username);

        if (user == null) {
            request.setAttribute("error", "Username not found!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (!user.getPassword().equals(password)) {
            request.setAttribute("error", "Wrong password!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Đăng nhập thành công
        request.getSession().setAttribute("user", user);

        // Xử lý Remember Me
        if (remember != null) {
            Cookie cookieUser = new Cookie("username", username);
            Cookie cookiePass = new Cookie("password", password);
            cookieUser.setMaxAge(7 * 24 * 60 * 60);
            cookiePass.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookieUser);
            response.addCookie(cookiePass);
        } else {
            Cookie cookieUser = new Cookie("username", "");
            Cookie cookiePass = new Cookie("password", "");
            cookieUser.setMaxAge(0);
            cookiePass.setMaxAge(0);
            response.addCookie(cookieUser);
            response.addCookie(cookiePass);
        }
        request.getSession().setAttribute("message", "Login successful!");

        String secureURL = (String) request.getSession().getAttribute("secureURL");
        if (secureURL != null) {
            request.getSession().removeAttribute("secureURL");
            response.sendRedirect(secureURL);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

}