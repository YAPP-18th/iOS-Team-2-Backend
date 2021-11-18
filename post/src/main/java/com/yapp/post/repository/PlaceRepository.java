package com.yapp.post.repository;

import com.yapp.post.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByNameAndLocation(String name, String location);

    List<Place> findAllByName(String name);

    boolean existsByNameAndLocation(String name, String location);
}
