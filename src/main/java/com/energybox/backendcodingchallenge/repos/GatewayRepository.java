package com.energybox.backendcodingchallenge.repos;

import com.energybox.backendcodingchallenge.domain.Gateway;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GatewayRepository extends Neo4jRepository<Gateway, Long> {

}
