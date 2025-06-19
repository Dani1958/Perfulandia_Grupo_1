package com.perfulandia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.model.Perfume;
import com.perfulandia.service.PerfumeService;

@RestController
@RequestMapping("/api/perfumes")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    //Buscar perfume por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@RequestParam String nombre) {
        try {
            List<Perfume> perfume = perfumeService.obtenerPorNombre(nombre);
            return ResponseEntity.ok(perfume);
        } catch(IllegalArgumentException except) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(except.getMessage());
        }
    }

    //Buscar perfume por id
    @GetMapping("/id/{id}")
    public Optional<Perfume> obtenerPorId(@RequestParam Long id) {
        return perfumeService.obtenerPorId(id);
    }

    //Listar todos los perfumes
    @GetMapping
    public List<Perfume> listarTodos() {
        return perfumeService.listarTodos();
    }

    //Buscar perfume por categoria
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> obtenerPorCategoria(@PathVariable String categoria) {
        try {
            List<Perfume> perfume = perfumeService.obtenerPorCategoria(categoria);
            return ResponseEntity.ok(perfume);
        } catch(IllegalArgumentException except) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(except.getMessage());
        }
    }
    
    //Guardar perfume
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Perfume perfume) {
        try {
            Perfume nuevoPerfume = perfumeService.guardar(perfume);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPerfume);
        } catch(IllegalArgumentException except) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(except.getMessage());
        }
    }

    //Actualizar perfume
    @PutMapping("/id/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Perfume perfume) {
        try {
            Perfume actualizarPerfume = perfumeService.actualizar(id, perfume);
            return ResponseEntity.ok(actualizarPerfume);
        } catch(IllegalArgumentException except) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(except.getMessage());
        }
    }

    //Eliminar perfume
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            perfumeService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException except) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(except.getMessage());
        }
    }
    
}
