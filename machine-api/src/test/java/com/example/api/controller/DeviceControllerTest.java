package com.example.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.example.machine.api.controller.MachineController;
import com.example.machine.api.service.MachineService;
import com.example.machine.domain.dto.MachineDTO;

class DeviceControllerTest {

    MachineService deviceService = Mockito.mock(MachineService.class);
    MachineController controller = new MachineController(deviceService);

    @Test
    void testGetAllDevices() {
        when(deviceService.getAllMachines()).thenReturn(List.of(new MachineDTO(1L, "Device1")));
        ResponseEntity<List<MachineDTO>> response = controller.getAllMachines();
        assertEquals(1, response.getBody().size());
        verify(deviceService, times(1)).getAllMachines();
    }

    @Test
    void testCreateDevice() {
    	MachineDTO dto = new MachineDTO(null, "NewDevice");
    	MachineDTO saved = new MachineDTO(1L, "NewDevice");
        when(deviceService.createMachine(dto)).thenReturn(saved);

        ResponseEntity<MachineDTO> response = controller.createMachine(dto);
        assertEquals(1L, response.getBody().getId());
        verify(deviceService, times(1)).createMachine(dto);
    }
}
