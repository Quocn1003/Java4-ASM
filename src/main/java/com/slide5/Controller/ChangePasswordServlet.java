package com.slide5.Controller;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slide5.Entity.User;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("java4");
    }

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id"); // username = id
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        EntityManager em = emf.createEntityManager();

        try {
            User user = em.find(User.class, id);

            if (user == null) {
                request.setAttribute("error", "User not found!");
                request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                return;
            }

            if (!user.getPassword().equals(currentPassword)) {
                request.setAttribute("error", "Current password is incorrect!");
                request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("error", "New password and confirm password do not match!");
                request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                return;
            }

            em.getTransaction().begin();
            user.setPassword(newPassword);
            em.merge(user);
            em.getTransaction().commit();

            // Gửi thông báo và quay lại trang login
            request.getSession().setAttribute("successMessage", "Password changed successfully! Please login again.");
            response.sendRedirect(request.getContextPath() + "/login");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error occurred while changing password!");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

}
