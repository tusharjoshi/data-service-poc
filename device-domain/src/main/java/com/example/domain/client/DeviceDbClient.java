package com.example.domain.client;

import com.example.domain.dto.DeviceDTO;
import com.example.domain.entity.Device;
import com.example.domain.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(name="device.client.type", havingValue="db", matchIfMissing=true)
@RequiredArgsConstructor
@Slf4j
public class DeviceDbClient implements DeviceClient {

    private final DeviceRepository deviceRepository;

    @Override
    public List<DeviceDTO> getAllDevices() {
        log.info("DeviceDbClient: fetching all devices from DB");
        List<DeviceDTO> devices = deviceRepository.findAll()
                .stream().map(d -> new DeviceDTO(d.getId(), d.getName()))
                .collect(Collectors.toList());
        log.info("DeviceDbClient: {} devices fetched", devices.size());
        return devices;
    }

    @Override
    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        log.info("DeviceDbClient: saving device with name {}", deviceDTO.getName());
        Device device = new Device();
        device.setName(deviceDTO.getName());
        Device saved = deviceRepository.save(device);
        log.info("DeviceDbClient: saved device ID {}", saved.getId());
        return new DeviceDTO(saved.getId(), saved.getName());
    }
}
