package com.sophialink.sophialink.controller;

import com.sophialink.sophialink.dto.ResenaDTO;
import com.sophialink.sophialink.dto.ResumenProfesorDTO;
import com.sophialink.sophialink.dto.ResumenCursoDTO;
import com.sophialink.sophialink.dto.EstadisticasGeneralesDTO;
import com.sophialink.sophialink.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resenas")
@CrossOrigin(origins = "*")
public class ResenaController {
    
    @Autowired
    private ResenaService resenaService;
    
    @GetMapping
    public ResponseEntity<List<ResenaDTO>> obtenerTodasLasResenas() {
        List<ResenaDTO> resenas = resenaService.obtenerTodasLasResenas();
        return ResponseEntity.ok(resenas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResenaDTO> obtenerResenaPorId(@PathVariable Integer id) {
        Optional<ResenaDTO> resena = resenaService.obtenerResenaPorId(id);
        return resena.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ResenaDTO>> obtenerResenasPorEstado(@PathVariable String estado) {
        try {
            List<ResenaDTO> resenas = resenaService.obtenerResenasPorEstado(estado);
            return ResponseEntity.ok(resenas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/profesor/{idProfesor}")
    public ResponseEntity<List<ResenaDTO>> obtenerResenasPorProfesor(@PathVariable Integer idProfesor) {
        try {
            List<ResenaDTO> resenas = resenaService.obtenerResenasPorProfesor(idProfesor);
            return ResponseEntity.ok(resenas);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ResenaDTO>> obtenerResenasPorUsuario(@PathVariable Integer idUsuario) {
        try {
            List<ResenaDTO> resenas = resenaService.obtenerResenasPorUsuario(idUsuario);
            return ResponseEntity.ok(resenas);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<ResenaDTO> crearResena(@RequestBody ResenaDTO resenaDTO) {
        try {
            ResenaDTO resenaCreada = resenaService.crearResena(resenaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resenaCreada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResenaDTO> actualizarResena(@PathVariable Integer id, 
                                                     @RequestBody ResenaDTO resenaDTO) {
        try {
            Optional<ResenaDTO> resenaActualizada = resenaService.actualizarResena(id, resenaDTO);
            return resenaActualizada.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}/moderar")
    public ResponseEntity<ResenaDTO> moderarResena(@PathVariable Integer id, 
                                                 @RequestParam String estado, 
                                                 @RequestParam Integer idAdmin) {
        try {
            Optional<ResenaDTO> resenaModerada = resenaService.moderarResena(id, estado, idAdmin);
            return resenaModerada.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Integer id) {
        boolean eliminado = resenaService.eliminarResena(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    // ========== ENDPOINTS DE BÚSQUEDA ==========
    
    @GetMapping("/buscar")
    public ResponseEntity<List<ResenaDTO>> buscarResenas(
            @RequestParam(required = false) String profesor,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Boolean utilidad) {
        
        try {
            List<ResenaDTO> resenas;
            
            // Búsqueda combinada por profesor y curso
            if (profesor != null && curso != null) {
                resenas = resenaService.buscarPorProfesorYCurso(profesor, curso);
            }
            // Búsqueda por profesor
            else if (profesor != null) {
                resenas = resenaService.buscarPorProfesor(profesor);
            }
            // Búsqueda por curso
            else if (curso != null) {
                resenas = resenaService.buscarPorCurso(curso);
            }
            // Búsqueda por utilidad
            else if (utilidad != null) {
                resenas = resenaService.buscarPorUtilidad(utilidad);
            }
            // Sin parámetros, retornar todas
            else {
                resenas = resenaService.obtenerTodasLasResenas();
            }
            
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/buscar/profesor/{nombreProfesor}")
    public ResponseEntity<List<ResenaDTO>> buscarPorProfesor(@PathVariable String nombreProfesor) {
        try {
            List<ResenaDTO> resenas = resenaService.buscarPorProfesor(nombreProfesor);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/buscar/curso/{nombreCurso}")
    public ResponseEntity<List<ResenaDTO>> buscarPorCurso(@PathVariable String nombreCurso) {
        try {
            List<ResenaDTO> resenas = resenaService.buscarPorCurso(nombreCurso);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/buscar/utilidad/{utilidad}")
    public ResponseEntity<List<ResenaDTO>> buscarPorUtilidad(@PathVariable Boolean utilidad) {
        try {
            List<ResenaDTO> resenas = resenaService.buscarPorUtilidad(utilidad);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/buscar/utilidad/{utilidad}/profesor/{nombreProfesor}")
    public ResponseEntity<List<ResenaDTO>> buscarPorUtilidadYProfesor(
            @PathVariable Boolean utilidad, 
            @PathVariable String nombreProfesor) {
        try {
            List<ResenaDTO> resenas = resenaService.buscarPorUtilidadYProfesor(utilidad, nombreProfesor);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/buscar/utilidad/{utilidad}/curso/{nombreCurso}")
    public ResponseEntity<List<ResenaDTO>> buscarPorUtilidadYCurso(
            @PathVariable Boolean utilidad, 
            @PathVariable String nombreCurso) {
        try {
            List<ResenaDTO> resenas = resenaService.buscarPorUtilidadYCurso(utilidad, nombreCurso);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // ========== ENDPOINTS DE REPORTES Y ESTADÍSTICAS ==========
    
    @GetMapping("/resumen/profesor/{idProfesor}")
    public ResponseEntity<ResumenProfesorDTO> obtenerResumenProfesor(@PathVariable Integer idProfesor) {
        try {
            ResumenProfesorDTO resumen = resenaService.obtenerResumenProfesor(idProfesor);
            return ResponseEntity.ok(resumen);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/resumen/curso/{idCurso}")
    public ResponseEntity<ResumenCursoDTO> obtenerResumenCurso(@PathVariable Integer idCurso) {
        try {
            ResumenCursoDTO resumen = resenaService.obtenerResumenCurso(idCurso);
            return ResponseEntity.ok(resumen);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/estadisticas/generales")
    public ResponseEntity<EstadisticasGeneralesDTO> obtenerEstadisticasGenerales() {
        try {
            EstadisticasGeneralesDTO estadisticas = resenaService.obtenerEstadisticasGenerales();
            return ResponseEntity.ok(estadisticas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/estadisticas/profesor/{idProfesor}/promedio")
    public ResponseEntity<Object> obtenerPromedioProfesor(@PathVariable Integer idProfesor) {
        try {
            ResumenProfesorDTO resumen = resenaService.obtenerResumenProfesor(idProfesor);
            // Redondear a 2 decimales
            BigDecimal promedioRedondeado = resumen.getPromedioGeneral().setScale(2, java.math.RoundingMode.HALF_UP);
            return ResponseEntity.ok(promedioRedondeado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/estadisticas/curso/{idCurso}/promedio")
    public ResponseEntity<Object> obtenerPromedioCurso(@PathVariable Integer idCurso) {
        try {
            ResumenCursoDTO resumen = resenaService.obtenerResumenCurso(idCurso);
            // Redondear a 2 decimales
            BigDecimal promedioRedondeado = resumen.getPromedioGeneral().setScale(2, java.math.RoundingMode.HALF_UP);
            return ResponseEntity.ok(promedioRedondeado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/estadisticas/utilidad")
    public ResponseEntity<Object> obtenerEstadisticasUtilidad() {
        try {
            EstadisticasGeneralesDTO estadisticas = resenaService.obtenerEstadisticasGenerales();
            return ResponseEntity.ok(estadisticas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/estadisticas/estados")
    public ResponseEntity<Object> obtenerEstadisticasEstados() {
        try {
            EstadisticasGeneralesDTO estadisticas = resenaService.obtenerEstadisticasGenerales();
            return ResponseEntity.ok(estadisticas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
