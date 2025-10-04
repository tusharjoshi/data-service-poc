package com.example.machine.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.machine.api.service.MachineService;
import com.example.machine.domain.dto.MachineDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/machines")
@RequiredArgsConstructor
@Slf4j
public class MachineController {

    private final MachineService machineService;

    @GetMapping("/returnall")
    public ResponseEntity<List<MachineDTO>> getAllMachines() {
        log.info("Controller: GET /api/machines called");
        List<MachineDTO> machines = machineService.getAllMachines();
        log.info("Controller: returning {} devices", machines.size());
        return ResponseEntity.ok(machines);
    }

    @PostMapping("/create")
    public ResponseEntity<MachineDTO> createMachine(@RequestBody MachineDTO machineDTO) {
        log.info("Controller: POST /api/machines called with name {}", machineDTO.getName());
        MachineDTO created = machineService.createMachine(machineDTO);
        log.info("Controller: returning created machine with ID {}", created.getId());
        return ResponseEntity.ok(created);
    }
}
