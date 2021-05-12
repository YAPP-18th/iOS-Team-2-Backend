package com.yapp.yongyong.domain.post.api;

import com.yapp.yongyong.domain.post.service.PlaceService;
import com.yapp.yongyong.domain.user.dto.TokenDto;
import com.yapp.yongyong.global.domain.CommonApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/place")
public class PlaceController {
    private final PlaceService placeService;

    @ApiOperation(value = "가게별 주제 쓰이는 용기들 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = TokenDto.class)
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('GUEST','USER')")
    public ResponseEntity<CommonApiResponse> addPost(@RequestParam String name, @RequestParam String place) {
        return ResponseEntity.ok(new CommonApiResponse(placeService.getBestContainersByPlace(name,place)));
    }
}
