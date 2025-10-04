package com.example.machine.domain.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.machine.domain.dto.MachineDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@ConditionalOnProperty(name="machine.client.type", havingValue="rest")
@RequiredArgsConstructor
@Slf4j
public class MachineRestClient implements MachineClient {

    private final RestTemplate machineRestTemplate;
    
    @Value("${data-service-url}")
    private String DATA_SERVICE_URL;
    @Value("${machine-service-end-point}")
    private String MACHINE_SERVICE_END_POINT;

    @Override
    public List<MachineDTO> getAllMachines() {
        log.info("DeviceRestClient: fetching all devices from DeviceDataService");
        MachineDTO[] devices = machineRestTemplate.getForObject(DATA_SERVICE_URL+MACHINE_SERVICE_END_POINT+"/returnall", MachineDTO[].class);
        log.info("DeviceRestClient: {} devices fetched", devices.length);
        return Arrays.asList(devices);
    }

    @Override
    public MachineDTO createMachine(MachineDTO deviceDTO) {
        log.info("DeviceRestClient: creating device via DeviceDataService with name {}", deviceDTO.getName());
        MachineDTO created = machineRestTemplate.postForObject(DATA_SERVICE_URL+MACHINE_SERVICE_END_POINT+"/create", deviceDTO, MachineDTO.class);
        log.info("DeviceRestClient: device created with ID {}", created.getId());
        return created;
    }
}
