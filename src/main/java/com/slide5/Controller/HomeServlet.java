package com.slide5.Controller;

import com.slide5.Service.VideoService;
import com.slide5.Entity.Video;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private VideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int size = 6;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Video> videos = videoService.findPage(page, size);
        long total = videoService.count();
        int totalPages = (int) Math.ceil((double) total / size);

        request.setAttribute("videos", videos);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("prevPage", page > 1 ? page - 1 : 1);
        request.setAttribute("nextPage", page < totalPages ? page + 1 : totalPages);

        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
