package com.energybox.backendcodingchallenge.model;

import com.energybox.backendcodingchallenge.domain.Sensor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GatewayDTO {
    private Long id;
    private String name;
}
