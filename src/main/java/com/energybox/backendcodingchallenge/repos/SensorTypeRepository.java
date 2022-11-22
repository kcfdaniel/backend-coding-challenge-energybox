package com.energybox.backendcodingchallenge.repos;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorType;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SensorTypeRepository extends Neo4jRepository<SensorType, Long> {

}
