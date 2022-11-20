package com.energybox.backendcodingchallenge.repos;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.model.SensorType;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Arrays;
import java.util.List;

public interface SensorRepository extends Neo4jRepository<Sensor, Long> {

    List<Sensor> findAllByGateway(Gateway gateway);

    List<Sensor> findAllByTypesContaining(SensorType sensorType);
}
