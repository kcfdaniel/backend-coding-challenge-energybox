package com.energybox.backendcodingchallenge.domain;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class LastReading {
    @Id @GeneratedValue
    private Long id;

    private Long timestamp;

    private Long value;

    @TargetNode
    private SensorType sensorType;
}
