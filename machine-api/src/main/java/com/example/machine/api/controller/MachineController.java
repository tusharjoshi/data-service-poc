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

    private final MachineService deviceService;

    @GetMapping("/returnall")
    public ResponseEntity<List<MachineDTO>> getAllMachines() {
        log.info("Controller: GET /api/devices called");
        List<MachineDTO> devices = deviceService.getAllMachines();
        log.info("Controller: returning {} devices", devices.size());
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/create")
    public ResponseEntity<MachineDTO> createMachine(@RequestBody MachineDTO deviceDTO) {
        log.info("Controller: POST /api/devices called with name {}", deviceDTO.getName());
        MachineDTO created = deviceService.createMachine(deviceDTO);
        log.info("Controller: returning created device with ID {}", created.getId());
        return ResponseEntity.ok(created);
    }
}
