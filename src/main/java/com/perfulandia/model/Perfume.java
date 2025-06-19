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
    private String nombre;
    private Integer stock;
    private String categoria;

}
