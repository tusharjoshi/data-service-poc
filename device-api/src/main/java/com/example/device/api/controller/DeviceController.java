package com.example.device.api.controller;

import com.example.device.api.service.DeviceService;
import com.example.device.domain.dto.DeviceDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/returnall")
    public ResponseEntity<List<DeviceDTO>> getAllDevices() {
        log.info("Controller: GET /api/devices called");
        List<DeviceDTO> devices = deviceService.getAllDevices();
        log.info("Controller: returning {} devices", devices.size());
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/create")
    public ResponseEntity<DeviceDTO> createDevice(@RequestBody DeviceDTO deviceDTO) {
        log.info("Controller: POST /api/devices called with name {}", deviceDTO.getName());
        DeviceDTO created = deviceService.createDevice(deviceDTO);
        log.info("Controller: returning created device with ID {}", created.getId());
        return ResponseEntity.ok(created);
    }
}
