package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.model.AddSensorTypeToSensorRequestDTO;
import com.energybox.backendcodingchallenge.model.SensorDTO;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation( summary = "get the list of sensors assigned to a gateway" )
    @GetMapping("/by-gateway/{gatewayId}")
    public ResponseEntity<List<SensorDTO>> getSensorsByGatewayId(@PathVariable final Long gatewayId) {
        return ResponseEntity.ok(sensorService.getByGatewayId(gatewayId));
    }

    @Operation( summary = "get a sensor with a specific type" )
    @GetMapping("/by-type/{sensorTypeId}")
    public ResponseEntity<List<SensorDTO>> getSensorsBySensorType(@PathVariable final Long sensorTypeId) {
        return ResponseEntity.ok(sensorService.getSensorsBySensorType(sensorTypeId));
    }

    @Operation( summary = "get a sensor by id" )
    @GetMapping("/{id}")
    public ResponseEntity<SensorDTO> getSensor(@PathVariable final Long id) {
        return ResponseEntity.ok(sensorService.get(id));
    }

    @Operation( summary = "create a sensor" )
    @PostMapping
    public ResponseEntity<Long> createSensor(@RequestBody @Valid final SensorDTO sensorDTO) {
        return new ResponseEntity<>(sensorService.create(sensorDTO), HttpStatus.CREATED);
    }

    @Operation( summary = "add a type to a sensor")
    @PutMapping("/{sensorId}/add-sensor-type")
    public ResponseEntity<Long> addSensorType(@PathVariable final Long sensorId, @RequestBody @Valid final AddSensorTypeToSensorRequestDTO requestDTO) {
        return new ResponseEntity<>(sensorService.addSensorType(sensorId, requestDTO.getSensorTypeId()), HttpStatus.CREATED);
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
