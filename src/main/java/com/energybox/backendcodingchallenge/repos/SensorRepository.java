package com.energybox.backendcodingchallenge.repos;

import com.energybox.backendcodingchallenge.domain.Sensor;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SensorRepository extends Neo4jRepository<Sensor, Long> {

}
