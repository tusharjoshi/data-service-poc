package com.example.machine.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "machine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Machine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
}
