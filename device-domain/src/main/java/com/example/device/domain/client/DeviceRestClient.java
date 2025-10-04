package com.example.device.domain.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.device.domain.dto.DeviceDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@ConditionalOnProperty(name="device.client.type", havingValue="rest")
@RequiredArgsConstructor
@Slf4j
public class DeviceRestClient implements DeviceClient {

    private final RestTemplate deviceRestTemplate;
    @Value("${data-service-url}")
    private String DATA_SERVICE_URL;
    @Value("${device-service-end-point}")
    private String DEVICE_SERVICE_END_POINT;
    
    @Override
    public List<DeviceDTO> getAllDevices() {
        log.info("DeviceRestClient: fetching all devices from DeviceDataService");
        DeviceDTO[] devices = deviceRestTemplate.getForObject(DATA_SERVICE_URL+DEVICE_SERVICE_END_POINT+"/returnall", DeviceDTO[].class);
        log.info("DeviceRestClient: {} devices fetched", devices.length);
        return Arrays.asList(devices);
    }

    @Override
    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        log.info("DeviceRestClient: creating device via DeviceDataService with name {}", deviceDTO.getName());
        DeviceDTO created = deviceRestTemplate.postForObject(DATA_SERVICE_URL+DEVICE_SERVICE_END_POINT+ "/create", deviceDTO, DeviceDTO.class);
        log.info("DeviceRestClient: device created with ID {}", created.getId());
        return created;
    }
}
