package com.yapp.yongyong.global.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @ApiModelProperty(hidden = true)
    @CreatedDate
    private LocalDateTime createdDate;

    @ApiModelProperty(hidden = true)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
