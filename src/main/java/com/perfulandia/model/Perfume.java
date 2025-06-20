package com.perfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "perfume")
@NoArgsConstructor
@AllArgsConstructor
public class Perfume {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private Integer stock;

    @Column(nullable = false, length = 100)
    private String categoria;

    @Column(nullable = false)
    private Boolean disponible; 

}
