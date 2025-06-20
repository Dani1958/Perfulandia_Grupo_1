package com.perfulandia.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.perfulandia.model.Perfume;
import com.perfulandia.repository.PerfumeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    //Buscar perfume por nombre
    public List<Perfume> obtenerPorNombre(String nombre) {
         List<Perfume> perfume = perfumeRepository.findByNombre(nombre);

         if (perfume.isEmpty()) {
            throw new IllegalArgumentException("No se encontró ningún perfume con el nombre " + nombre);
         }

         return perfume;
    }

    //Buscar perfume por disponibilidad
    public List<Perfume> obtenerPorDisponibilidad(Boolean disponible) {
        if (disponible != true || disponible != false) {
            throw new IllegalArgumentException("Solo puede ser disponible (true) o no disponible (false)");
        }

        List<Perfume> perfume = perfumeRepository.findByDisponible(disponible);

        if (perfume.isEmpty()) {
            throw new IllegalArgumentException("No existen perfumes " + disponible);
        }

        return perfume;
    }

    //Listar todos los perfumes
    public List<Perfume> listarTodos() {
        List<Perfume> perfume = perfumeRepository.findAll();

        if (perfume.isEmpty()) {
            throw new IllegalArgumentException("No hay perfumes registrados.");
        }

        return perfume;
    }

    //Buscar perfume por categoria
    public List<Perfume> obtenerPorCategoria(String categoria) {
        if (!"Mujer".equalsIgnoreCase(categoria) && 
            !"Hombre".equalsIgnoreCase(categoria)) {
            throw new IllegalArgumentException("La categoría debe ser 'Mujer' o 'Hombre'");
        }

        return perfumeRepository.findByCategoria(categoria);
    }

    //Guardar perfume
    public Perfume guardar(Perfume perfume) {
        boolean existsByNombre = perfumeRepository.findByNombre(perfume.getNombre())
            .stream()
            .anyMatch(perf -> perf.getNombre().equalsIgnoreCase(perfume.getNombre()));

        if (perfume.getId() != null && perfumeRepository.existsById(perfume.getId())) {
            throw new IllegalArgumentException("Ya existe un perfume con el id " + perfume.getId());
        }

        if (existsByNombre) {
            throw new IllegalArgumentException("Ya existe un perfume con el nombre " + perfume.getNombre());
        }

        if (!"Mujer".equalsIgnoreCase(perfume.getCategoria()) && 
            !"Hombre".equalsIgnoreCase(perfume.getCategoria())) {
            throw new IllegalArgumentException("La categoría debe ser 'Mujer' o 'Hombre'");
        }
        
        perfume.setDisponible(perfume.getStock() != null && perfume.getStock() > 0);

        return perfumeRepository.save(perfume);
    }

    //Actualizar perfume
    public Perfume actualizar(Long id, Perfume perfume) {
        boolean existsByNombre = perfumeRepository.findByNombre(perfume.getNombre())
            .stream()
            .anyMatch(perf -> !perf.getId().equals(id) && perf.getNombre().equalsIgnoreCase(perfume.getNombre()));

        if (existsByNombre) {
            throw new IllegalArgumentException("Ya existe un perfume con el nombre " + perfume.getNombre());
        }

        if (!"Mujer".equalsIgnoreCase(perfume.getCategoria()) && 
            !"Hombre".equalsIgnoreCase(perfume.getCategoria())) {
            throw new IllegalArgumentException("La categoría debe ser 'Mujer' o 'Hombre'");
        }

        perfume.setId(id);
        perfume.setDisponible(perfume.getStock() != null && perfume.getStock() > 0);
        return perfumeRepository.save(perfume);
    }

    //Eliminar perfume
    public void eliminar(Long id) {
        if (!perfumeRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe un perfume con el id " + id);
        }
        
        perfumeRepository.deleteById(id);
    }
    
}
