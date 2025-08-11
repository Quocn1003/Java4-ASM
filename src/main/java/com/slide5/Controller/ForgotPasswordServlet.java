package com.slide5.Controller;

import com.slide5.Entity.User;
import com.slide5.Service.MailService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("java4");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");

        EntityManager em = emf.createEntityManager();
        try {
            User user = em.createQuery(
                    "SELECT u FROM User u WHERE u.id = :username AND u.email = :email", User.class)
                    .setParameter("username", username)
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (user != null) {
                try {
                    MailService mailService = new MailService();
                    String subject = "Forgot password - ONLINE ENTERTAINMENT";
                    String content = "Your password: <b>" + user.getPassword() + "</b>";
                    mailService.sendEmails(new String[] { email }, subject, content);
                    request.setAttribute("success", "Your password has been sent to your email.");
                } catch (Exception ex) {
                    request.setAttribute("error", "Send email not success." + ex.getMessage());
                }
            } else {
                request.setAttribute("error", "Wrong Username or Email.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred. Please try again.");
        } finally {
            em.close();
        }

        request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
    }
}
