package com.yapp.yongyong.domain.post.repository;

import com.yapp.yongyong.domain.post.entity.Post;
import com.yapp.yongyong.domain.post.entity.LikePost;
import com.yapp.yongyong.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    boolean existsByUserAndPost(User user, Post post);

    void deleteByUserAndPost(User user, Post post);

    void deleteAllByUser(User user);
}
