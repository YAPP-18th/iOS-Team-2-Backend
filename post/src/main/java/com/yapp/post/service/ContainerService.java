package com.yapp.post.service;

import com.yapp.post.dto.ContainerDto;
import com.yapp.post.entity.ContainerName;
import com.yapp.post.entity.ContainerSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ContainerService {
    public List<ContainerDto> getContainers() {
        List<ContainerDto> containers = new ArrayList<>();
        for (ContainerName containerName : ContainerName.values()) {
            for (ContainerSize containerSize : ContainerSize.values()) {
                containers.add(new ContainerDto(containerName.getName(), containerSize.name()));
            }
        }
        return containers;
    }
}
