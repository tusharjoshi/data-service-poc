package com.example.api.controller;

import com.example.api.service.DeviceService;
import com.example.domain.dto.DeviceDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DeviceControllerTest {

    DeviceService deviceService = Mockito.mock(DeviceService.class);
    DeviceController controller = new DeviceController(deviceService);

    @Test
    void testGetAllDevices() {
        when(deviceService.getAllDevices()).thenReturn(List.of(new DeviceDTO(1L, "Device1")));
        ResponseEntity<List<DeviceDTO>> response = controller.getAllDevices();
        assertEquals(1, response.getBody().size());
        verify(deviceService, times(1)).getAllDevices();
    }

    @Test
    void testCreateDevice() {
        DeviceDTO dto = new DeviceDTO(null, "NewDevice");
        DeviceDTO saved = new DeviceDTO(1L, "NewDevice");
        when(deviceService.createDevice(dto)).thenReturn(saved);

        ResponseEntity<DeviceDTO> response = controller.createDevice(dto);
        assertEquals(1L, response.getBody().getId());
        verify(deviceService, times(1)).createDevice(dto);
    }
}
