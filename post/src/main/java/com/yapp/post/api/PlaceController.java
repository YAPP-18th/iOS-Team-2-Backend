package com.yapp.post.api;


import com.yapp.post.service.PlaceService;
import com.yapp.post.global.entity.CommonApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/place")
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<CommonApiResponse> getContainers(@RequestParam String name, @RequestParam String place) {
        return ResponseEntity.ok(new CommonApiResponse(placeService.getBestContainersByPlace(name, place)));
    }

    @GetMapping("/review-count")
    public ResponseEntity<CommonApiResponse> getReviewCounts(@RequestParam String name) {
        return ResponseEntity.ok(new CommonApiResponse(placeService.getReviewCountsByName(name)));
    }

    @GetMapping("/review-count/all")
    public ResponseEntity<CommonApiResponse> getAllReviewCounts() {
        return ResponseEntity.ok(new CommonApiResponse(placeService.getAllReviewCounts()));
    }
}
