package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.model.SensorDTO;
import com.energybox.backendcodingchallenge.model.SensorType;
import com.energybox.backendcodingchallenge.repos.GatewayRepository;
import com.energybox.backendcodingchallenge.repos.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorService {

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    GatewayRepository gatewayRepository;

    public List<SensorDTO> findAll() {
        return sensorRepository.findAll(Sort.by("id"))
                .stream()
                .map(sensor -> mapToDTO(sensor, new SensorDTO()))
                .collect(Collectors.toList());
    }

    public SensorDTO get(final Long id) {
        return sensorRepository.findById(id)
                .map(sensor -> mapToDTO(sensor, new SensorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SensorDTO sensorDTO) {
        final Sensor sensor = new Sensor();
        mapToEntity(sensorDTO, sensor);
        return sensorRepository.save(sensor).getId();
    }

    public void update(final Long id, final SensorDTO sensorDTO) {
        final Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(sensorDTO, sensor);
        sensorRepository.save(sensor);
    }

    public void delete(final Long id) {
        sensorRepository.deleteById(id);
    }

    private SensorDTO mapToDTO(final Sensor sensor, final SensorDTO sensorDTO) {
        sensorDTO.setId(sensor.getId());
        sensorDTO.setName(sensor.getName());
        sensorDTO.setTypes(sensor.getTypes());
        return sensorDTO;
    }

    private Sensor mapToEntity(final SensorDTO sensorDTO, final Sensor sensor) {
        sensor.setName(sensorDTO.getName());
        sensor.setTypes(sensorDTO.getTypes());
        return sensor;
    }

    public List<SensorDTO> getByGatewayId(Long id) {
        Gateway gateway = gatewayRepository.findById(id).get();
        return gateway.getSensors().stream()
                .map(sensor -> mapToDTO(sensor, new SensorDTO()))
                .collect(Collectors.toList());
    }

    public List<SensorDTO> getSensorsBySensorType(SensorType sensorType) {
        return sensorRepository.findAllByTypesContaining(sensorType)
                .stream()
                .map(sensor -> mapToDTO(sensor, new SensorDTO()))
                .collect(Collectors.toList());
    }
}
