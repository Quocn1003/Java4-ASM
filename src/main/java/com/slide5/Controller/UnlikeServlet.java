package com.slide5.Controller;

import com.slide5.Service.FavouriteService;
import com.slide5.Entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/unlike")
public class UnlikeServlet extends HttpServlet {
    private FavouriteService favouriteService = new FavouriteService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        String userId = user.getId();
        String videoId = request.getParameter("id");
        favouriteService.removeFavourite(userId, videoId);
        session.setAttribute("message", "Removed from favourites!");
        response.sendRedirect("myFavourite");
    }
}
