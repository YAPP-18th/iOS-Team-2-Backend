package com.yapp.post.repository;

import com.yapp.post.entity.Post;
import com.yapp.post.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    boolean existsByUserIdAndPost(Long userId, Post post);

    void deleteByUserIdAndPost(Long userId, Post post);

    void deleteAllByUserId(Long userId);
}
