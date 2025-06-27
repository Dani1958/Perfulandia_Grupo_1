package com.perfulandia.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.perfulandia.model.Perfume;
import com.perfulandia.repository.PerfumeRepository;


@Service
public class PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    //Buscar perfume por nombre
    public List<Perfume> obtenerPorNombre(String nombre) {
        List<Perfume> perfume = perfumeRepository.findByNombre(nombre);

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (perfume.isEmpty()) {
            throw new IllegalArgumentException("No se encontró ningún perfume con el nombre " + nombre);
        }

        return perfume;
    }

    //Buscar perfume por disponibilidad
    public List<Perfume> obtenerPorDisponibilidad(String disponible) {
        if (disponible == null || 
            !"Disponible".equalsIgnoreCase(disponible) &&
            !"No disponible".equalsIgnoreCase(disponible)) {
            throw new IllegalArgumentException("Solo puede ser disponible o no disponible");
        }

        List<Perfume> perfume = perfumeRepository.findByDisponible(disponible);

        if (perfume.isEmpty()) {
            throw new IllegalArgumentException("No existen perfumes " + disponible);
        }

        return perfume;
    }

    //Buscar perfume por categoria
    public List<Perfume> obtenerPorCategoria(String categoria) {
        if (categoria == null ||
            !"Mujer".equalsIgnoreCase(categoria) && 
            !"Hombre".equalsIgnoreCase(categoria)) {
            throw new IllegalArgumentException("La categoría debe ser 'Mujer' o 'Hombre'");
        }

        return perfumeRepository.findByCategoria(categoria);
    }

    //Listar todos los perfumes
    public List<Perfume> listarTodos() {
        List<Perfume> perfume = perfumeRepository.findAll();

        if (perfume.isEmpty()) {
            throw new IllegalArgumentException("No hay perfumes registrados.");
        }

        return perfume;
    }

    

    //Guardar perfume
    public Perfume guardar(Perfume perfume) {
        boolean existsByNombre = perfumeRepository.findByNombre(perfume.getNombre())
            .stream()
            .anyMatch(perf -> perf.getNombre().equalsIgnoreCase(perfume.getNombre()));

        if (existsByNombre) {
            throw new IllegalArgumentException("Ya existe un perfume con el nombre " + perfume.getNombre());
        }

        if (perfume.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser un número positivo");
        }

        if (!"Mujer".equalsIgnoreCase(perfume.getCategoria()) && 
            !"Hombre".equalsIgnoreCase(perfume.getCategoria())) {
            throw new IllegalArgumentException("La categoría debe ser 'Mujer' o 'Hombre'");
        }

        if (perfume.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser menor a cero");
        }
        
        if (perfume.getStock() > 0) {
            perfume.setDisponible("Disponible");
        }
        else {
            perfume.setDisponible("No disponible");
        }

        if (perfume.getNombre() == null || perfume.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        
        if (perfume.getMarca() == null || perfume.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("La marca es obligatoria");
        }
        
        if (perfume.getPrecio() == null) {
            throw new IllegalArgumentException("El precio es obligatorio");
        }

        return perfumeRepository.save(perfume);
    }

    //Actualizar perfume
    public Perfume actualizar(Long id, Perfume perfume) {
        if (!perfumeRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe un perfume con el id " + id);
        }
        
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

        if (perfume.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser un número positivo");
        }

        if (perfume.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser menor a cero");
        }

        if (perfume.getStock() > 0) {
            perfume.setDisponible("Disponible");
        }
        else {
            perfume.setDisponible("No disponible");
        }

        if (perfume.getNombre() == null || perfume.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        
        if (perfume.getMarca() == null || perfume.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("La marca es obligatoria");
        }
        
        if (perfume.getPrecio() == null) {
            throw new IllegalArgumentException("El precio es obligatorio");
        }

        perfume.setId(id);
        return perfumeRepository.save(perfume);
    }

    //Eliminar perfume con id
    public void eliminar(Long id) {
        if (!perfumeRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe un perfume con el id " + id);
        }
        
        perfumeRepository.deleteById(id);
    }
    
}
