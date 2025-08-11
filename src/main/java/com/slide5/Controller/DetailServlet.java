package com.slide5.Controller;

import com.slide5.Entity.Video;
import com.slide5.Service.VideoService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    private VideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String videoId = request.getParameter("id");
        if (videoId == null || videoId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing video ID");
            return;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("java4");
        EntityManager em = emf.createEntityManager();
        Video video = em.find(Video.class, videoId);
        if (video == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
            em.close();
            emf.close();
            return;
        }

        List<Video> topVideos = videoService.findTop5();
        request.setAttribute("topVideos", topVideos);

        request.setAttribute("video", video);
        em.close();
        emf.close();
        request.getRequestDispatcher("/detail.jsp").forward(request, response);
    }
}
