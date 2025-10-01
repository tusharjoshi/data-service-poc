package com.example.api.service;

import com.example.domain.client.DeviceClient;
import com.example.domain.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceService {

    private final DeviceClient deviceClient;

    public List<DeviceDTO> getAllDevices() {
        log.info("API DeviceService: fetching all devices");
        List<DeviceDTO> devices = deviceClient.getAllDevices();
        log.info("API DeviceService: {} devices fetched", devices.size());
        return devices;
    }

    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        log.info("API DeviceService: creating device with name {}", deviceDTO.getName());
        DeviceDTO created = deviceClient.createDevice(deviceDTO);
        log.info("API DeviceService: device created with ID {}", created.getId());
        return created;
    }
}
