package com.yapp.post.repository;

import com.yapp.post.entity.Post;
import com.yapp.post.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {


    boolean existsByUserAndPost(Long userId, Post post);

    void deleteByUserAndPost(Long userId, Post post);

    void deleteAllByUser(Long userId);
}
