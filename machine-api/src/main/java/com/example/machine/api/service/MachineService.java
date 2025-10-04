package com.example.machine.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.machine.domain.client.MachineClient;
import com.example.machine.domain.dto.MachineDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MachineService {

    private final MachineClient machineClient;

    public List<MachineDTO> getAllMachines() {
        log.info("API MachineService: fetching all machines");
        List<MachineDTO> devices = machineClient.getAllMachines();
        log.info("API MachineService: {} machines fetched", devices.size());
        return devices;
    }

    public MachineDTO createMachine(MachineDTO machineDTO) {
        log.info("API DeviceService: creating machine with name {}", machineDTO.getName());
        MachineDTO created = machineClient.createMachine(machineDTO);
        log.info("API MachineService: machine created with ID {}", created.getId());
        return created;
    }
}
