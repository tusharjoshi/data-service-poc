package com.example.machine.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.machine.domain.dto.MachineDTO;
import com.example.machine.domain.entity.Machine;
import com.example.machine.domain.repository.MachineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/machines")
@ConditionalOnProperty(name="data-service-mode", havingValue="true")
@RequiredArgsConstructor
@Slf4j
public class MachineDataService {

    private final MachineRepository machineRepository;

    @GetMapping("/returnall")
    public ResponseEntity<List<MachineDTO>> getAllDevices() {
        log.info("MachineDataService: GET /machines called");
        List<MachineDTO> devices = machineRepository.findAll()
                .stream().map(d -> new MachineDTO(d.getId(), d.getName()))
                .collect(Collectors.toList());
        log.info("MachineDataService: returning {} machines", devices.size());
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/create")
    public ResponseEntity<MachineDTO> createDevice(@RequestBody MachineDTO deviceDTO) {
        log.info("MachineDataService: POST /machines called with name {}", deviceDTO.getName());
        Machine machine = new Machine();
        machine.setName(deviceDTO.getName());
        Machine saved = machineRepository.save(machine);
        log.info("MachineDataService: saved machine ID {}", saved.getId());
        return ResponseEntity.ok(new MachineDTO(saved.getId(), saved.getName()));
    }
}
