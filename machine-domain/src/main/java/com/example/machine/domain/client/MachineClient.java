package com.example.machine.domain.client;

import java.util.List;

import com.example.machine.domain.dto.MachineDTO;

public interface MachineClient {
    List<MachineDTO> getAllMachines();
    MachineDTO createMachine(MachineDTO deviceDTO);
}
