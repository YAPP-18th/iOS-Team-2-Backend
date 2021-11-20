package com.yapp.yongyong.domain.user.repository;

import com.yapp.yongyong.domain.user.entity.TermsOfService;
import com.yapp.yongyong.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsOfServiceRepository extends JpaRepository<TermsOfService, Long> {
    void deleteByUser(User user);
}
