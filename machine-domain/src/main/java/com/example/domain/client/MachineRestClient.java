package com.example.domain.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.domain.dto.MachineDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@ConditionalOnProperty(name="machine.client.type", havingValue="rest")
@RequiredArgsConstructor
@Slf4j
public class MachineRestClient implements MachineClient {

    private final RestTemplate restTemplate;
   // private final String BASE_URL = "http://localhost:8081/internal/devices";
    private final String BASE_URL = "http://localhost:8082/api/v1/machine";
    @Override
    public List<MachineDTO> getAllMachines() {
        log.info("DeviceRestClient: fetching all devices from DeviceDataService");
        MachineDTO[] devices = restTemplate.getForObject(BASE_URL+"/returnall", MachineDTO[].class);
        log.info("DeviceRestClient: {} devices fetched", devices.length);
        return Arrays.asList(devices);
    }

    @Override
    public MachineDTO createMachine(MachineDTO deviceDTO) {
        log.info("DeviceRestClient: creating device via DeviceDataService with name {}", deviceDTO.getName());
        MachineDTO created = restTemplate.postForObject(BASE_URL, deviceDTO, MachineDTO.class);
        log.info("DeviceRestClient: device created with ID {}", created.getId());
        return created;
    }
}
