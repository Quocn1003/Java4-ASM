package com.slide5.Controller;

import com.slide5.Entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {
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
        // Chuyển tới trang editProfile.jsp
        request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        String username = request.getParameter("username"); // username chính là id trong Entity
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");

        EntityManager em = emf.createEntityManager();

        try {
            User user = em.find(User.class, username);

            if (user == null) {
                request.setAttribute("error", "User not found!");
                request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
                return;
            }

            // Cập nhật thông tin user
            em.getTransaction().begin();
            user.setPassword(password);
            user.setFullname(fullname);
            user.setEmail(email);
            em.getTransaction().commit();

            // Cập nhật lại user trong session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            request.setAttribute("success", "Profile updated successfully!");
            request.getRequestDispatcher("/editProfile.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error occurred while updating profile!");
            request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }
}
