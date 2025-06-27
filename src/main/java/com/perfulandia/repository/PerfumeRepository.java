package com.perfulandia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.perfulandia.model.Perfume;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long>{

    //findByNombreContainingIgnoreCase con este nombre ignora las mayúsculas y minúsculas
    //Segun yo no es necesario cambiarlo porque en el service ocupo equalsIgnoreCase que hace lo mismo
    List<Perfume> findByNombre(String nombre);

    List<Perfume> findByCategoria(String categoria);

    List<Perfume> findByDisponible(String disponible);
}
