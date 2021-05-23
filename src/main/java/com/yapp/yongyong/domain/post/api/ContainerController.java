package com.yapp.yongyong.domain.post.api;

import com.yapp.yongyong.domain.post.dto.ContainerDto;
import com.yapp.yongyong.domain.post.service.ContainerService;
import com.yapp.yongyong.global.domain.CommonApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/container")
public class ContainerController {
    private final ContainerService containerService;

    @ApiOperation(value = "용기들 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = ContainerDto.class, responseContainer = "List")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('GUEST','USER')")
    public ResponseEntity<CommonApiResponse> getContainers() {
        return ResponseEntity.ok(new CommonApiResponse(containerService.getContainers()));
    }
}
