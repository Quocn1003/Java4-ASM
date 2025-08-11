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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nếu đã login thì redirect về home
        if (request.getSession(false) != null && request.getSession(false).getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false) != null && request.getSession(false).getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");

        UserDAO userDAO = new UserService();

        // Kiểm tra username tồn tại chưa
        if (userDAO.findById(username) != null) {
            request.setAttribute("error", "Username đã tồn tại!");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
            return;
        }

        // Kiểm tra email tồn tại chưa
        if (userDAO.findByEmail(email) != null) {
            request.setAttribute("error", "Email đã được sử dụng!");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
            return;
        }

        // Tạo user mới
        User user = new User();
        user.setId(username);
        user.setPassword(password);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setAdmin(false); // Mặc định là user thường

        // Lưu vào DB
        userDAO.save(user);

        // Lưu user vào session (auto login)
        request.getSession().setAttribute("user", user);

        // Lưu cookie username và password
        Cookie cookieUser = new Cookie("username", username);
        Cookie cookiePass = new Cookie("password", password);
        cookieUser.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
        cookiePass.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookieUser);
        response.addCookie(cookiePass);

        request.getSession().setAttribute("message", "Đăng ký thành công! Bạn đã được đăng nhập.");
        // Chuyển hướng sau khi đăng ký thành công
        String secureURL = (String) request.getSession().getAttribute("secureURL");
        if (secureURL != null) {
            request.getSession().removeAttribute("secureURL");
            response.sendRedirect(secureURL);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
