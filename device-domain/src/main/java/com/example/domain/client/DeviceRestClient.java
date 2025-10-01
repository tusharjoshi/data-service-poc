package com.example.domain.client;

import com.example.domain.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(name="device.client.type", havingValue="rest")
@RequiredArgsConstructor
@Slf4j
public class DeviceRestClient implements DeviceClient {

    private final RestTemplate restTemplate;
   // private final String BASE_URL = "http://localhost:8081/internal/devices";
    private final String BASE_URL = "http://localhost:8082/api/v1/machine";
    @Override
    public List<DeviceDTO> getAllDevices() {
        log.info("DeviceRestClient: fetching all devices from DeviceDataService");
        DeviceDTO[] devices = restTemplate.getForObject(BASE_URL+"/returnall", DeviceDTO[].class);
        log.info("DeviceRestClient: {} devices fetched", devices.length);
        return Arrays.asList(devices);
    }

    @Override
    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        log.info("DeviceRestClient: creating device via DeviceDataService with name {}", deviceDTO.getName());
        DeviceDTO created = restTemplate.postForObject(BASE_URL, deviceDTO, DeviceDTO.class);
        log.info("DeviceRestClient: device created with ID {}", created.getId());
        return created;
    }
}
