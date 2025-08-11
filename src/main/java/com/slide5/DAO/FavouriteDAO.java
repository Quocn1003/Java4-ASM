package com.slide5.DAO;

import java.util.List;

public interface FavouriteDAO {

    // Define methods for favourite data access operations
    void addFavourite(String userId, String videoId);

    void removeFavourite(String userId, String videoId);

    List<String> getFavouritesByUserId(String userId);

    boolean isFavourite(String userId, String videoId);
}
