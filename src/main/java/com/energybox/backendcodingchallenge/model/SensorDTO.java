package com.energybox.backendcodingchallenge.model;

import com.energybox.backendcodingchallenge.domain.Gateway;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SensorDTO {
    private Long id;
    private String name;
    private Set<String> types = new HashSet<>();
}
