package com.sophialink.sophialink.controller;

import com.sophialink.sophialink.dto.UniversidadDTO;
import com.sophialink.sophialink.service.UniversidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/universidades")
@CrossOrigin(origins = "*")
public class UniversidadController {
    
    @Autowired
    private UniversidadService universidadService;
    
    @GetMapping
    public ResponseEntity<List<UniversidadDTO>> obtenerTodasLasUniversidades() {
        List<UniversidadDTO> universidades = universidadService.obtenerTodasLasUniversidades();
        return ResponseEntity.ok(universidades);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UniversidadDTO> obtenerUniversidadPorId(@PathVariable Integer id) {
        Optional<UniversidadDTO> universidad = universidadService.obtenerUniversidadPorId(id);
        return universidad.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<UniversidadDTO> crearUniversidad(@RequestBody UniversidadDTO universidadDTO) {
        try {
            UniversidadDTO universidadCreada = universidadService.crearUniversidad(universidadDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(universidadCreada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UniversidadDTO> actualizarUniversidad(@PathVariable Integer id, 
                                                               @RequestBody UniversidadDTO universidadDTO) {
        Optional<UniversidadDTO> universidadActualizada = universidadService.actualizarUniversidad(id, universidadDTO);
        return universidadActualizada.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUniversidad(@PathVariable Integer id) {
        boolean eliminado = universidadService.eliminarUniversidad(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
