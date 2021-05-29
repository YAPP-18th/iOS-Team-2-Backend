package com.yapp.yongyong.domain.user.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "authorities")
@Entity
public class Authority {

    @ApiModelProperty(hidden = true)
    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}
