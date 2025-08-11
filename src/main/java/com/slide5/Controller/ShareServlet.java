package com.slide5.Controller;

import com.slide5.Entity.User;
import com.slide5.Entity.Video;
import com.slide5.Service.MailService;
import com.slide5.Service.VideoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {
    private VideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String videoId = request.getParameter("id");
        if (videoId == null || videoId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id video is required for sharing");
            return;
        }
        Video video = videoService.findById(videoId);
        if (video == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
            return;
        }
        request.setAttribute("video", video);
        request.getRequestDispatcher("/share.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String videoId = request.getParameter("id");
        String emailInput = request.getParameter("email");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        if (videoId == null || videoId.isEmpty()) {
            request.setAttribute("error", "Id video is required for sharing");
            doGet(request, response);
            return;
        }
        if (emailInput == null || emailInput.isEmpty()) {
            request.setAttribute("error", "Please enter email");
            doGet(request, response);
            return;
        }

        String[] emailArr = emailInput.split("[,;]\s*");
        Video video = videoService.findById(videoId);
        if (video == null) {
            request.setAttribute("error", "Video not found");
            doGet(request, response);
            return;
        }
        String videoLink = request.getRequestURL().toString().replace("share", "detail") + "?id=" + videoId;
        String subject = "Share: " + video.getTitle();
        String content = "From: " + user.getFullname() + ": <a href='" + videoLink + "'>"
                + video.getTitle() + "</a>";

        MailService mailService = new MailService();
        try {
            mailService.sendEmails(emailArr, subject, content);
            session.setAttribute("successMessage", "Video shared successfully!");
        } catch (Exception e) {
            request.setAttribute("error", "Failse to send: " + e.getMessage());
            doGet(request, response);
            return;
        }
        response.sendRedirect("share?id=" + videoId);
    }
}
