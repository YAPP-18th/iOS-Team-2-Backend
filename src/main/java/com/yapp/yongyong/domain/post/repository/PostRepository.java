package com.yapp.yongyong.domain.post.repository;

import com.yapp.yongyong.domain.post.entity.Place;
import com.yapp.yongyong.domain.post.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"postContainers", "postImages", "comments", "user"})
    List<Post> findAllByPlace(Place place);

    @EntityGraph(attributePaths = {"postContainers", "postImages", "comments", "user"})
    List<Post> findAllByUser_Nickname(String nickname);

    @EntityGraph(attributePaths = {"postContainers", "postImages", "comments", "user"})
    List<Post> findAllByUser_Id(Long userId);
}
