package com.energybox.backendcodingchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

@Getter
@Setter
@AllArgsConstructor
public class LastReading {
    @Id @GeneratedValue
    private Long id;

    private Long timestamp;

    private Long value;

    @TargetNode
    private Sensor sensor;
}
