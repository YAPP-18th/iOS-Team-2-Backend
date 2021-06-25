package com.yapp.yongyong.domain.user.repository;

import com.yapp.yongyong.domain.user.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
