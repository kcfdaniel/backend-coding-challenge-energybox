package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorType;
import com.energybox.backendcodingchallenge.model.SensorDTO;
import com.energybox.backendcodingchallenge.model.SensorTypeDTO;
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
public class SensorTypeService {

    @Autowired
    SensorTypeRepository sensorTypeRepository;

    @Autowired
    GatewayRepository gatewayRepository;

    public List<SensorTypeDTO> findAll() {
        return sensorTypeRepository.findAll(Sort.by("id"))
                .stream()
                .map(sensorType -> mapToDTO(sensorType, new SensorTypeDTO()))
                .collect(Collectors.toList());
    }

    public SensorTypeDTO get(final Long id) {
        return sensorTypeRepository.findById(id)
                .map(sensorType -> mapToDTO(sensorType, new SensorTypeDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SensorTypeDTO sensorTypeDTO) {
        final SensorType sensorType = new SensorType();
        mapToEntity(sensorTypeDTO, sensorType);
        return sensorTypeRepository.save(sensorType).getId();
    }

    public void update(final Long id, final SensorTypeDTO sensorTypeDTO) {
        final SensorType sensorType = sensorTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(sensorTypeDTO, sensorType);
        sensorTypeRepository.save(sensorType);
    }

    public void delete(final Long id) {
        sensorTypeRepository.deleteById(id);
    }

    private SensorTypeDTO mapToDTO(final SensorType sensorType, final SensorTypeDTO sensorTypeDTO) {
        sensorTypeDTO.setId(sensorType.getId());
        sensorTypeDTO.setName(sensorType.getName());
        return sensorTypeDTO;
    }

    private SensorType mapToEntity(final SensorTypeDTO sensorTypeDTO, final SensorType sensorType) {
        sensorType.setName(sensorTypeDTO.getName());
        return sensorType;
    }
}
