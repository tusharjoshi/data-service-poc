package com.example.domain.client;

import com.example.domain.dto.DeviceDTO;
import java.util.List;

public interface DeviceClient {
    List<DeviceDTO> getAllDevices();
    DeviceDTO createDevice(DeviceDTO deviceDTO);
}
