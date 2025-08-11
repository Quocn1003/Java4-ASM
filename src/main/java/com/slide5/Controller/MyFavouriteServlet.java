package com.slide5.Controller;

import com.slide5.Service.FavouriteService;
import com.slide5.Entity.Video;
import com.slide5.Entity.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/myFavourite")
public class MyFavouriteServlet extends HttpServlet {
    private FavouriteService favouriteService = new FavouriteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        String userId = user.getId();
        int page = 1;
        int size = 6;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Video> allVideos = favouriteService.getFavouriteVideosByUserId(userId);
        int total = allVideos.size();
        int totalPages = total == 0 ? 0 : (int) Math.ceil((double) total / size);
        int currentPage = total == 0 ? 0 : page;
        int fromIndex = total == 0 ? 0 : (page - 1) * size;
        int toIndex = total == 0 ? 0 : Math.min(fromIndex + size, total);
        List<Video> videos = total == 0 ? java.util.Collections.emptyList() : allVideos.subList(fromIndex, toIndex);
        request.setAttribute("videos", videos);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("prevPage", currentPage > 1 ? currentPage - 1 : 1);
        request.setAttribute("nextPage", currentPage < totalPages ? currentPage + 1 : totalPages);
        request.getRequestDispatcher("/myFavourite.jsp").forward(request, response);
    }
}
