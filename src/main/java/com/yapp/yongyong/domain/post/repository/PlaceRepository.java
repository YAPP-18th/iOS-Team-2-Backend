package com.yapp.yongyong.domain.post.repository;

import com.yapp.yongyong.domain.post.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByNameAndLocation(String name, String location);

    boolean existsByNameAndLocation(String name, String location);
}
