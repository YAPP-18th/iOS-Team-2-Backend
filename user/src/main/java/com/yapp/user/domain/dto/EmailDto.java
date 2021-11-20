package com.yapp.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDto {
    private String emailAddress;
    private String subject;
    private String content;
}
