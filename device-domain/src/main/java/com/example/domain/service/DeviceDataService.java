package com.example.domain.service;

import com.example.domain.dto.DeviceDTO;
import com.example.domain.entity.Device;
import com.example.domain.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/internal/devices")
@RequiredArgsConstructor
@Slf4j
public class DeviceDataService {

    private final DeviceRepository deviceRepository;

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> getAllDevices() {
        log.info("DeviceDataService: GET /internal/devices called");
        List<DeviceDTO> devices = deviceRepository.findAll()
                .stream().map(d -> new DeviceDTO(d.getId(), d.getName()))
                .collect(Collectors.toList());
        log.info("DeviceDataService: returning {} devices", devices.size());
        return ResponseEntity.ok(devices);
    }

    @PostMapping
    public ResponseEntity<DeviceDTO> createDevice(@RequestBody DeviceDTO deviceDTO) {
        log.info("DeviceDataService: POST /internal/devices called with name {}", deviceDTO.getName());
        Device device = new Device();
        device.setName(deviceDTO.getName());
        Device saved = deviceRepository.save(device);
        log.info("DeviceDataService: saved device ID {}", saved.getId());
        return ResponseEntity.ok(new DeviceDTO(saved.getId(), saved.getName()));
    }
}
