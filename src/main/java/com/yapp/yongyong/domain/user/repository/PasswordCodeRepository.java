package com.yapp.yongyong.domain.user.repository;

import com.yapp.yongyong.domain.user.entity.PasswordCode;
import org.springframework.data.repository.CrudRepository;

public interface PasswordCodeRepository extends CrudRepository<PasswordCode,String> {
}
