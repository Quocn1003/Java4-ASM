package com.slide5.DAO;

import java.util.List;

import com.slide5.Entity.Video;

public interface VideoDAO {
    // Define methods for video data access operations
    Video findById(String id);

    List<Video> findByUserId(String userId);

    void save(Video video);

    void update(Video video);

    void delete(String id);

    List<Video> findAll();

    public List<Video> findTop6();

    public List<Video> findPage(int page, int size);

    public long count();
}
