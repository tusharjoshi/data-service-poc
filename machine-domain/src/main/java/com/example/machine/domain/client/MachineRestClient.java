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
        log.info("MachineRestClient: fetching all machines from MachineDataService");
        MachineDTO[] machines = machineRestTemplate.getForObject(DATA_SERVICE_URL+MACHINE_SERVICE_END_POINT+"/returnall", MachineDTO[].class);
        log.info("MachineRestClient: {} machines fetched", machines.length);
        return Arrays.asList(machines);
    }

    @Override
    public MachineDTO createMachine(MachineDTO machineDTO) {
        log.info("MachineRestClient: creating machine via MachineDataService with name {}", machineDTO.getName());
        MachineDTO created = machineRestTemplate.postForObject(DATA_SERVICE_URL+MACHINE_SERVICE_END_POINT+"/create", machineDTO, MachineDTO.class);
        log.info("MachineRestClient: machine created with ID {}", created.getId());
        return created;
    }
}
