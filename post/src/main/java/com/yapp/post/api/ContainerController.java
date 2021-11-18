package com.yapp.post.api;

import com.yapp.post.global.entity.CommonApiResponse;
import com.yapp.post.service.ContainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/container")
public class ContainerController {
    private final ContainerService containerService;

    @GetMapping
    public ResponseEntity<CommonApiResponse> getContainers() {
        return ResponseEntity.ok(new CommonApiResponse(containerService.getContainers()));
    }
}
