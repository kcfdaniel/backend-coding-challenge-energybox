package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.model.SensorDTO;
import com.energybox.backendcodingchallenge.model.SensorType;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.service.SensorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( value = "/sensors" )
public class SensorController {

    @Autowired
    SensorService sensorService;

    @Autowired
    GatewayService gatewayService;

    @GetMapping
    public ResponseEntity<List<SensorDTO>> getAllSensors() {
        return ResponseEntity.ok(sensorService.findAll());
    }

    @ApiOperation( value = "get a sensor assigned to a gateway" )
    @GetMapping("/by-gateway/{gatewayId}")
    public ResponseEntity<List<SensorDTO>> getSensorsByGatewayId(@PathVariable final Long gatewayId) {
        return ResponseEntity.ok(sensorService.getByGatewayId(gatewayId));
    }

    @ApiOperation( value = "get a sensor with a specific type" )
    @GetMapping("/by-type/{type}")
    public ResponseEntity<List<SensorDTO>> getSensorsBySensorType(@PathVariable final SensorType sensorType) {
        return ResponseEntity.ok(sensorService.getSensorsBySensorType(sensorType));
    }

    @ApiOperation( value = "get the sensors connected to a gateway" )
    @GetMapping("/{id}")
    public ResponseEntity<SensorDTO> getSensor(@PathVariable final Long id) {
        return ResponseEntity.ok(sensorService.get(id));
    }

    @ApiOperation( value = "create a sensor" )
    @PostMapping
    public ResponseEntity<Long> createSensor(@RequestBody @Valid final SensorDTO sensorDTO) {
        return new ResponseEntity<>(sensorService.create(sensorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSensor(@PathVariable final Long id,
                                             @RequestBody @Valid final SensorDTO sensorDTO) {
        sensorService.update(id, sensorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable final Long id) {
        sensorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
