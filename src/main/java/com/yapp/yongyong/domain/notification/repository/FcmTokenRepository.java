package com.yapp.yongyong.domain.notification.repository;

import com.yapp.yongyong.domain.notification.entity.FcmToken;
import org.springframework.data.repository.CrudRepository;

public interface FcmTokenRepository extends CrudRepository<FcmToken, Long> {
}
