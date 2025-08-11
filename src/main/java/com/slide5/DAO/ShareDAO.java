package com.slide5.DAO;

import java.util.List;

public interface ShareDAO {

    // Define methods for share data access operations
    void shareVideo(String userId, String videoId);

    void unshareVideo(String userId, String videoId);

    List<String> getSharedVideosByUserId(String userId);

    boolean isVideoShared(String userId, String videoId);
}
