package com.yapp.user.domain.repository;

import com.yapp.user.domain.entity.TermsOfService;
import com.yapp.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsOfServiceRepository extends JpaRepository<TermsOfService, Long> {
    void deleteByUser(User user);
}
