package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.model.AddSensorToGatewayRequestDTO;
import com.energybox.backendcodingchallenge.model.GatewayDTO;
import com.energybox.backendcodingchallenge.service.GatewayService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( value = "/gateways" )
public class GatewayController {

    @Autowired
    GatewayService gatewayService;

    @Operation( summary = "get all gateways")
    @GetMapping
    public ResponseEntity<List<GatewayDTO>> getAllGateways() {
        return ResponseEntity.ok(gatewayService.findAll());
    }

    @Operation( summary = "get a gateway by id")
    @GetMapping("/{id}")
    public ResponseEntity<GatewayDTO> getGateway(@PathVariable final Long id) {
        return ResponseEntity.ok(gatewayService.get(id));
    }

    @Operation( summary = "get the list of gateways by sensor type")
    @GetMapping("/by-sensor-type/{sensorTypeId}")
    public ResponseEntity<List<GatewayDTO>> getGatewayBySensorType(@PathVariable final Long sensorTypeId) {
        return ResponseEntity.ok(gatewayService.getGatewayBySensorType(sensorTypeId));
    }

    @Operation( summary = "create a gateway")
    @PostMapping
    public ResponseEntity<Long> createGateway(@RequestBody @Valid final GatewayDTO gatewayDTO) {
        return new ResponseEntity<>(gatewayService.create(gatewayDTO), HttpStatus.CREATED);
    }

    @Operation( summary = "add a sensor to a gateway")
    @PutMapping("/{gatewayId}/add-sensor")
    public ResponseEntity<Long> addSensor(@PathVariable final Long gatewayId, @RequestBody @Valid final AddSensorToGatewayRequestDTO requestDTO) {
        return new ResponseEntity<>(gatewayService.addSensor(gatewayId, requestDTO.getSensorId()), HttpStatus.CREATED);
    }

    @Operation( summary = "update a gateway")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGateway(@PathVariable final Long id,
                                              @RequestBody @Valid final GatewayDTO gatewayDTO) {
        gatewayService.update(id, gatewayDTO);
        return ResponseEntity.ok().build();
    }

    @Operation( summary = "delete a gateway")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGateway(@PathVariable final Long id) {
        gatewayService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
