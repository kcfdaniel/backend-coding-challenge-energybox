package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.model.GatewayDTO;
import com.energybox.backendcodingchallenge.model.SensorType;
import com.energybox.backendcodingchallenge.service.GatewayService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation( value = "get all gateways")
    @GetMapping
    public ResponseEntity<List<GatewayDTO>> getAllGateways() {
        return ResponseEntity.ok(gatewayService.findAll());
    }

    @ApiOperation( value = "get a gateway by id")
    @GetMapping("/{id}")
    public ResponseEntity<GatewayDTO> getGateway(@PathVariable final Long id) {
        return ResponseEntity.ok(gatewayService.get(id));
    }

    @ApiOperation( value = "get a gateway by sensor type")
    @GetMapping("/{id}")
    public ResponseEntity<List<GatewayDTO>> getGatewayBySensorType(@PathVariable final SensorType sensorType) {
        return ResponseEntity.ok(gatewayService.getGatewayBySensorType(sensorType));
    }

    @ApiOperation( value = "create a gateway")
    @PostMapping
    public ResponseEntity<Long> createGateway(@RequestBody @Valid final GatewayDTO gatewayDTO) {
        return new ResponseEntity<>(gatewayService.create(gatewayDTO), HttpStatus.CREATED);
    }

    @ApiOperation( value = "add a sensor to a gateway")
    @PostMapping("/{gatewayId}/add-sensor/{sensorId}")
    public ResponseEntity<Long> addSensor(@RequestBody @Valid final Long gatewayId, @RequestBody @Valid final Long sensorId) {
        return new ResponseEntity<>(gatewayService.addSensor(gatewayId, sensorId), HttpStatus.CREATED);
    }

    @ApiOperation( value = "update a gateway")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGateway(@PathVariable final Long id,
                                              @RequestBody @Valid final GatewayDTO gatewayDTO) {
        gatewayService.update(id, gatewayDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation( value = "delete a gateway")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGateway(@PathVariable final Long id) {
        gatewayService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
