package com.yapp.yongyong.domain.post.service;

import com.yapp.yongyong.domain.post.entity.ContainerName;
import com.yapp.yongyong.domain.post.entity.ContainerSize;
import com.yapp.yongyong.domain.post.dto.ContainerDto;
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
