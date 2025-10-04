package com.example.device.domain.service;

import com.example.device.domain.dto.DeviceDTO;
import com.example.device.domain.entity.Device;
import com.example.device.domain.repository.DeviceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/devices")
@ConditionalOnProperty(name="data-service-mode", havingValue="true")
@RequiredArgsConstructor
@Slf4j
public class DeviceDataService {

    private final DeviceRepository deviceRepository;

    @GetMapping("/returnall")
    public ResponseEntity<List<DeviceDTO>> getAllDevices() {
        log.info("DeviceDataService: GET /devices called");
        List<DeviceDTO> devices = deviceRepository.findAll()
                .stream().map(d -> new DeviceDTO(d.getId(), d.getName()))
                .collect(Collectors.toList());
        log.info("DeviceDataService: returning {} devices", devices.size());
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/create")
    public ResponseEntity<DeviceDTO> createDevice(@RequestBody DeviceDTO deviceDTO) {
        log.info("DeviceDataService: POST /devices called with name {}", deviceDTO.getName());
        Device device = new Device();
        device.setName(deviceDTO.getName());
        Device saved = deviceRepository.save(device);
        log.info("DeviceDataService: saved device ID {}", saved.getId());
        return ResponseEntity.ok(new DeviceDTO(saved.getId(), saved.getName()));
    }
}
