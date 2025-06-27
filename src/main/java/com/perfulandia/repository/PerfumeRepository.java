package com.perfulandia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.perfulandia.model.Perfume;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long>{

    List<Perfume> findByNombreContainingIgnoreCase(String nombre);

    List<Perfume> findByCategoriaContainingIgnoreCase(String categoria);

    List<Perfume> findByDisponibleIgnoreCase(String disponible);
}
