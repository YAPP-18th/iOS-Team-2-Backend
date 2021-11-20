package com.yapp.yongyong.domain.post.repository;

import com.yapp.yongyong.domain.post.entity.Place;
import com.yapp.yongyong.domain.post.entity.Post;
import com.yapp.yongyong.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    @EntityGraph(attributePaths = {"postContainers", "postImages", "comments", "user", "place", "likePosts"})
    List<Post> findAllByPlace(Place place);

    @EntityGraph(attributePaths = {"postContainers", "postImages", "comments", "user", "place", "likePosts"})
    List<Post> findAllByUser_Nickname(String nickname);

    @EntityGraph(attributePaths = {"postContainers", "postImages", "comments", "user", "place", "likePosts"})
    List<Post> findAllByUser_Id(Long userId);

    void deleteAllByUser(User user);
}
