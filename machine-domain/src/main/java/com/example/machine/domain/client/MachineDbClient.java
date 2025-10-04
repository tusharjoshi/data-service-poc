package com.example.machine.domain.client;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.machine.domain.dto.MachineDTO;
import com.example.machine.domain.entity.Machine;
import com.example.machine.domain.repository.MachineRepository;

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
        log.info("MachineDbClient: fetching all machines from DB");
        List<MachineDTO> machines = machineRepository.findAll()
                .stream().map(d -> new MachineDTO(d.getId(), d.getName()))
                .collect(Collectors.toList());
        log.info("MachineDbClient: {} machines fetched", machines.size());
        return machines;
    }

    @Override
    public MachineDTO createMachine(MachineDTO machineDTO) {
        log.info("MachineDbClient: saving machine with name {}", machineDTO.getName());
        Machine machine = new Machine();
        machine.setName(machineDTO.getName());
        Machine saved = machineRepository.save(machine);
        log.info("DeviceDbClient: saved machine ID {}", saved.getId());
        return new MachineDTO(saved.getId(), saved.getName());
    }
}
