package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.model.SensorDTO;
import com.energybox.backendcodingchallenge.model.SensorTypeDTO;
import com.energybox.backendcodingchallenge.service.SensorTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( value = "/sensor-types" )
public class SensorTypeController {

    @Autowired
    SensorTypeService sensorTypeService;

    @GetMapping
    public ResponseEntity<List<SensorTypeDTO>> getAllSensorTypes() {
        return ResponseEntity.ok(sensorTypeService.findAll());
    }

    @ApiOperation( value = "get a sensor type by id" )
    @GetMapping("/{id}")
    public ResponseEntity<SensorTypeDTO> getSensorType(@PathVariable final Long id) {
        return ResponseEntity.ok(sensorTypeService.get(id));
    }

    @ApiOperation( value = "create a sensor type" )
    @PostMapping
    public ResponseEntity<Long> createSensorType(@RequestBody @Valid final SensorTypeDTO sensorTypeDTO) {
        return new ResponseEntity<>(sensorTypeService.create(sensorTypeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSensorType(@PathVariable final Long id,
                                             @RequestBody @Valid final SensorTypeDTO sensorTypeDTO) {
        sensorTypeService.update(id, sensorTypeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensorType(@PathVariable final Long id) {
        sensorTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
