package com.example.device.domain.client;

import java.util.List;

import com.example.device.domain.dto.DeviceDTO;

public interface DeviceClient {
    List<DeviceDTO> getAllDevices();
    DeviceDTO createDevice(DeviceDTO deviceDTO);
}
