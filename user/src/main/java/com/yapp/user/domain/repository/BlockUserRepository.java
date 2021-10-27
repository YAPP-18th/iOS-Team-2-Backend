package com.yapp.user.domain.repository;

import com.yapp.user.domain.entity.BlockUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockUserRepository extends JpaRepository<BlockUser, Long> {
}
