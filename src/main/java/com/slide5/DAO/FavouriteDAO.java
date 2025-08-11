package com.slide5.DAO;

import java.util.List;

import com.slide5.Entity.Video;

public interface FavouriteDAO {

    // Define methods for favourite data access operations
    void addFavourite(String userId, String videoId);

    void removeFavourite(String userId, String videoId);

    List<Video> getFavouriteVideosByUserId(String userId);

    boolean isFavourite(String userId, String videoId);
}
