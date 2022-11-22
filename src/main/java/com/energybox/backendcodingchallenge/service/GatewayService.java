package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorType;
import com.energybox.backendcodingchallenge.model.GatewayDTO;
import com.energybox.backendcodingchallenge.repos.GatewayRepository;
import com.energybox.backendcodingchallenge.repos.SensorRepository;
import com.energybox.backendcodingchallenge.repos.SensorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GatewayService {

    @Autowired
    GatewayRepository gatewayRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    SensorTypeRepository sensorTypeRepository;

    public List<GatewayDTO> findAll() {
        return gatewayRepository.findAll(Sort.by("id"))
                .stream()
                .map(gateway -> mapToDTO(gateway, new GatewayDTO()))
                .collect(Collectors.toList());
    }

    public GatewayDTO get(final Long id) {
        return gatewayRepository.findById(id)
                .map(gateway -> mapToDTO(gateway, new GatewayDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final GatewayDTO gatewayDTO) {
        final Gateway gateway = new Gateway();
        mapToEntity(gatewayDTO, gateway);
        return gatewayRepository.save(gateway).getId();
    }

    public void update(final Long id, final GatewayDTO gatewayDTO) {
        final Gateway gateway = gatewayRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(gatewayDTO, gateway);
        gatewayRepository.save(gateway);
    }

    public void delete(final Long id) {
        gatewayRepository.deleteById(id);
    }

    private GatewayDTO mapToDTO(final Gateway gateway, final GatewayDTO gatewayDTO) {
        gatewayDTO.setId(gateway.getId());
        gatewayDTO.setName(gateway.getName());
        return gatewayDTO;
    }

    private Gateway mapToEntity(final GatewayDTO gatewayDTO, final Gateway gateway) {
        gateway.setName(gatewayDTO.getName());
        return gateway;
    }

    public Long addSensor(Long gatewayId, Long sensorId) {
        Gateway gateway = gatewayRepository.findById(gatewayId).get();
        Sensor sensor = sensorRepository.findById(sensorId).get();
        gateway.getSensors().add(sensor);
        return gatewayRepository.save(gateway).getId();
    }

    public List<GatewayDTO> getGatewayBySensorType(Long sensorTypeId) {
        SensorType sensorType = sensorTypeRepository.findById(sensorTypeId).get();
        return sensorType.getLastReadingReversed()
                .stream().map(lastReadingReversed -> lastReadingReversed.getSensor())
                .collect(Collectors.toList())
                .stream().map(sensor -> sensor.getGateway())
                .collect(Collectors.toList())
                .stream().distinct().map(gateway -> mapToDTO(gateway, new GatewayDTO()))
                .collect(Collectors.toList());
    }
}