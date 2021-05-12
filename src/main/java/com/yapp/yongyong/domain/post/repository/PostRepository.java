package com.yapp.yongyong.domain.post.repository;

import com.yapp.yongyong.domain.post.domain.Place;
import com.yapp.yongyong.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByPlace(Place place);
}
