package com.yapp.post.service;

import com.yapp.post.dto.ReviewCountDto;
import com.yapp.post.entity.Place;
import com.yapp.post.dto.ContainerDto;
import com.yapp.post.repository.PlaceRepository;
import com.yapp.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PostRepository postRepository;

    public List<ContainerDto> getBestContainersByPlace(String name, String location) {
        Optional<Place> findPlace = placeRepository.findByNameAndLocation(name, location);

        if (findPlace.isEmpty()) {
            return Collections.emptyList();
        }

        List<ContainerDto> collect = postRepository.findAllByPlace(findPlace.get()).stream()
                .flatMap(post -> post.getPostContainers().stream())
                .collect(Collectors.toMap(postContainer -> new ContainerDto(postContainer.getContainerName(), postContainer.getContainerSize()),
                        postContainer -> 1, Integer::sum))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<ContainerDto, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return collect.subList(0, Math.min(collect.size(), 3));
    }

    public List<Place> getReviewCountsByName(String name) {
        return placeRepository.findAllByName(name);
    }

    public List<ReviewCountDto> getAllReviewCounts() {
        return placeRepository.findAll().stream()
                .map(place -> new ReviewCountDto(place.getName(), place.getLocation(), place.getReviewCount()))
                .collect(Collectors.toList());
    }
}
