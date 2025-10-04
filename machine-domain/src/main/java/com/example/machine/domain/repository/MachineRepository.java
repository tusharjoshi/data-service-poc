package com.example.machine.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.machine.domain.entity.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
}
