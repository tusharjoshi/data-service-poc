package com.example.domain.client;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.domain.dto.MachineDTO;
import com.example.domain.entity.Machine;
import com.example.domain.repository.MachineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@ConditionalOnProperty(name="machine.client.type", havingValue="db", matchIfMissing=true)
@RequiredArgsConstructor
@Slf4j
public class MachineDbClient implements MachineClient {

    private final MachineRepository machineRepository;

    @Override
    public List<MachineDTO> getAllMachines() {
        log.info("DeviceDbClient: fetching all devices from DB");
        List<MachineDTO> devices = machineRepository.findAll()
                .stream().map(d -> new MachineDTO(d.getId(), d.getName()))
                .collect(Collectors.toList());
        log.info("DeviceDbClient: {} devices fetched", devices.size());
        return devices;
    }

    @Override
    public MachineDTO createMachine(MachineDTO deviceDTO) {
        log.info("DeviceDbClient: saving device with name {}", deviceDTO.getName());
        Machine machine = new Machine();
        machine.setName(deviceDTO.getName());
        Machine saved = machineRepository.save(machine);
        log.info("DeviceDbClient: saved device ID {}", saved.getId());
        return new MachineDTO(saved.getId(), saved.getName());
    }
}
