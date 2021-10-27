package com.yapp.user.domain.repository;

import com.yapp.user.domain.entity.PasswordCode;
import org.springframework.data.repository.CrudRepository;

public interface PasswordCodeRepository extends CrudRepository<PasswordCode, String> {
}
