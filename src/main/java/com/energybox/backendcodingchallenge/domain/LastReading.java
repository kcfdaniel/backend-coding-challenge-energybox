package com.energybox.backendcodingchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@RelationshipProperties
public class LastReading {
    @Id
    @GeneratedValue
    private Long id;

    private String sensorType;

    @TargetNode
    private Sensor sensor;
}
