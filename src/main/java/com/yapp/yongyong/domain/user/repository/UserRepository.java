package com.yapp.yongyong.domain.user.repository;

import com.yapp.yongyong.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
