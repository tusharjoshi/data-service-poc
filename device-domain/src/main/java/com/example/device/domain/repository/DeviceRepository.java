package com.example.device.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.device.domain.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
