package com.yapp.yongyong.domain.post.service;

import com.yapp.yongyong.domain.post.entity.Place;
import com.yapp.yongyong.domain.post.dto.ContainerDto;
import com.yapp.yongyong.domain.post.repository.PlaceRepository;
import com.yapp.yongyong.domain.post.repository.PostRepository;
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
            return Arrays.asList();
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

        return collect.subList(0, collect.size() > 3 ? 3 : collect.size());
    }

    public List<Place> getReviewCountsByName(String name) {
        return placeRepository.findAllByName(name);
    }
}
