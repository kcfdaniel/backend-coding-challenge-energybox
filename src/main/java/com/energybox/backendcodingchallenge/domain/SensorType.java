package com.energybox.backendcodingchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING)
    private List<LastReadingReversed> lastReadingReversed;
}
