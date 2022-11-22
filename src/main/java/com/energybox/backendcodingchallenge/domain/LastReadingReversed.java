package com.energybox.backendcodingchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Getter
@Setter
@AllArgsConstructor
@RelationshipProperties
public class LastReadingReversed {
    @Id @GeneratedValue
    private Long id;

    private Long timestamp;

    private Long value;

    @TargetNode
    private Sensor sensor;
}
