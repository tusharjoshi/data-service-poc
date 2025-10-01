package com.example.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.client.MachineClient;
import com.example.domain.dto.MachineDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MachineService {

    private final MachineClient machineClient;

    public List<MachineDTO> getAllMachines() {
        log.info("API DeviceService: fetching all devices");
        List<MachineDTO> devices = machineClient.getAllMachines();
        log.info("API DeviceService: {} devices fetched", devices.size());
        return devices;
    }

    public MachineDTO createMachine(MachineDTO deviceDTO) {
        log.info("API DeviceService: creating device with name {}", deviceDTO.getName());
        MachineDTO created = machineClient.createMachine(deviceDTO);
        log.info("API DeviceService: device created with ID {}", created.getId());
        return created;
    }
}
