package com.sophialink.sophialink.service;

import com.sophialink.sophialink.dto.ResenaDTO;
import com.sophialink.sophialink.dto.ResumenProfesorDTO;
import com.sophialink.sophialink.dto.ResumenCursoDTO;
import com.sophialink.sophialink.dto.EstadisticasGeneralesDTO;
import com.sophialink.sophialink.entity.Resena;
import com.sophialink.sophialink.entity.Usuario;
import com.sophialink.sophialink.entity.Profesor;
import com.sophialink.sophialink.entity.Curso;
import com.sophialink.sophialink.entity.Administrador;
import com.sophialink.sophialink.repository.ResenaRepository;
import com.sophialink.sophialink.repository.UsuarioRepository;
import com.sophialink.sophialink.repository.ProfesorRepository;
import com.sophialink.sophialink.repository.CursoRepository;
import com.sophialink.sophialink.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResenaService {
    
    @Autowired
    private ResenaRepository resenaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProfesorRepository profesorRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    public List<ResenaDTO> obtenerTodasLasResenas() {
        List<Resena> resenas = resenaRepository.findAll();
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public Optional<ResenaDTO> obtenerResenaPorId(Integer id) {
        return resenaRepository.findById(id)
                .map(this::convertirADTO);
    }
    
    public List<ResenaDTO> obtenerResenasPorEstado(String estado) {
        Resena.EstadoResena estadoEnum = Resena.EstadoResena.valueOf(estado.toUpperCase());
        List<Resena> resenas = resenaRepository.findByEstado(estadoEnum);
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ResenaDTO> obtenerResenasPorProfesor(Integer idProfesor) {
        Profesor profesor = profesorRepository.findById(idProfesor)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        List<Resena> resenas = resenaRepository.findByProfesor(profesor);
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ResenaDTO> obtenerResenasPorUsuario(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Resena> resenas = resenaRepository.findByUsuario(usuario);
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public ResenaDTO crearResena(ResenaDTO resenaDTO) {
        // Validar calificaciones
        validarCalificaciones(resenaDTO.getCalifAmabilidad(), resenaDTO.getCalifClaridad(), resenaDTO.getCalifExigencia());
        
        // Validar que existan las entidades relacionadas
        Usuario usuario = usuarioRepository.findById(resenaDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        Profesor profesor = profesorRepository.findById(resenaDTO.getIdProfesor())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        
        Curso curso = cursoRepository.findById(resenaDTO.getIdCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        
        // Crear la resena
        Resena resena = new Resena();
        resena.setUsuario(usuario);
        resena.setProfesor(profesor);
        resena.setCurso(curso);
        resena.setCalifAmabilidad(resenaDTO.getCalifAmabilidad());
        resena.setCalifClaridad(resenaDTO.getCalifClaridad());
        resena.setCalifExigencia(resenaDTO.getCalifExigencia());
        resena.setComentario(resenaDTO.getComentario());
        resena.setEsAnonimo(resenaDTO.getEsAnonimo() != null ? resenaDTO.getEsAnonimo() : false);
        resena.setUtilidad(resenaDTO.getUtilidad() != null ? resenaDTO.getUtilidad() : false);
        resena.setEstado(Resena.EstadoResena.PENDIENTE);
        
        Resena resenaGuardada = resenaRepository.save(resena);
        return convertirADTO(resenaGuardada);
    }
    
    public Optional<ResenaDTO> actualizarResena(Integer id, ResenaDTO resenaDTO) {
        return resenaRepository.findById(id)
                .map(resena -> {
                    // Validar calificaciones
                    validarCalificaciones(resenaDTO.getCalifAmabilidad(), resenaDTO.getCalifClaridad(), resenaDTO.getCalifExigencia());
                    
                    // Actualizar solo los campos permitidos
                    resena.setCalifAmabilidad(resenaDTO.getCalifAmabilidad());
                    resena.setCalifClaridad(resenaDTO.getCalifClaridad());
                    resena.setCalifExigencia(resenaDTO.getCalifExigencia());
                    resena.setComentario(resenaDTO.getComentario());
                    resena.setEsAnonimo(resenaDTO.getEsAnonimo());
                    
                    Resena resenaActualizada = resenaRepository.save(resena);
                    return convertirADTO(resenaActualizada);
                });
    }
    
    public Optional<ResenaDTO> moderarResena(Integer id, String estado, Integer idAdmin) {
        return resenaRepository.findById(id)
                .map(resena -> {
                    Administrador admin = administradorRepository.findById(idAdmin)
                            .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
                    
                    resena.setEstado(Resena.EstadoResena.valueOf(estado.toUpperCase()));
                    resena.setAdminRevisor(admin);
                    
                    Resena resenaModerada = resenaRepository.save(resena);
                    return convertirADTO(resenaModerada);
                });
    }
    
    public boolean eliminarResena(Integer id) {
        if (resenaRepository.existsById(id)) {
            resenaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // ========== MÉTODOS DE BÚSQUEDA ==========
    
    public List<ResenaDTO> buscarPorProfesor(String nombreProfesor) {
        List<Resena> resenas = resenaRepository.findByProfesorNombreContainingIgnoreCase(nombreProfesor);
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ResenaDTO> buscarPorCurso(String nombreCurso) {
        List<Resena> resenas = resenaRepository.findByCursoNombreContainingIgnoreCase(nombreCurso);
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ResenaDTO> buscarPorProfesorYCurso(String nombreProfesor, String nombreCurso) {
        List<Resena> resenas = resenaRepository.findByProfesorAndCursoNombreContainingIgnoreCase(nombreProfesor, nombreCurso);
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ResenaDTO> buscarPorUtilidad(Boolean utilidad) {
        List<Resena> resenas = resenaRepository.findByUtilidad(utilidad);
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ResenaDTO> buscarPorUtilidadYProfesor(Boolean utilidad, String nombreProfesor) {
        List<Resena> resenas = resenaRepository.findByUtilidadAndProfesorNombreContainingIgnoreCase(utilidad, nombreProfesor);
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ResenaDTO> buscarPorUtilidadYCurso(Boolean utilidad, String nombreCurso) {
        List<Resena> resenas = resenaRepository.findByUtilidadAndCursoNombreContainingIgnoreCase(utilidad, nombreCurso);
        return resenas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    // ========== MÉTODOS DE ESTADÍSTICAS Y REPORTES ==========
    
    public ResumenProfesorDTO obtenerResumenProfesor(Integer idProfesor) {
        Profesor profesor = profesorRepository.findById(idProfesor)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        
        // Obtener estadísticas básicas
        Long totalResenas = resenaRepository.countByProfesorId(idProfesor);
        if (totalResenas == 0) {
            return new ResumenProfesorDTO(idProfesor, profesor.getNombreCompleto(), 0, 
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    0, 0, BigDecimal.ZERO, 0, 0, 0, null, "Sin datos", "Sin datos");
        }
        
        // Promedios de calificaciones
        Double avgAmabilidad = resenaRepository.getPromedioAmabilidadByProfesor(idProfesor);
        Double avgClaridad = resenaRepository.getPromedioClaridadByProfesor(idProfesor);
        Double avgExigencia = resenaRepository.getPromedioExigenciaByProfesor(idProfesor);
        
        BigDecimal promedioAmabilidad = avgAmabilidad != null ? BigDecimal.valueOf(avgAmabilidad).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        BigDecimal promedioClaridad = avgClaridad != null ? BigDecimal.valueOf(avgClaridad).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        BigDecimal promedioExigencia = avgExigencia != null ? BigDecimal.valueOf(avgExigencia).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        BigDecimal promedioGeneral = promedioAmabilidad.add(promedioClaridad).add(promedioExigencia).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
        
        // Estadísticas de utilidad
        Long resenasUtiles = resenaRepository.countResenasUtilesByProfesor(idProfesor);
        Long resenasNoUtiles = resenaRepository.countResenasNoUtilesByProfesor(idProfesor);
        BigDecimal porcentajeUtilidad = totalResenas > 0 ? 
                BigDecimal.valueOf(resenasUtiles).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(totalResenas), 2, RoundingMode.HALF_UP) : 
                BigDecimal.ZERO;
        
        // Estados de resenas
        Long resenasAprobadas = resenaRepository.countByProfesorAndEstado(idProfesor, Resena.EstadoResena.APROBADA);
        Long resenasPendientes = resenaRepository.countByProfesorAndEstado(idProfesor, Resena.EstadoResena.PENDIENTE);
        Long resenasRechazadas = resenaRepository.countByProfesorAndEstado(idProfesor, Resena.EstadoResena.RECHAZADA);
        
        // Última resena
        LocalDateTime ultimaResena = resenaRepository.getUltimaResenaByProfesor(idProfesor);
        
        // Análisis de aspectos
        String mejorAspecto = determinarMejorAspecto(promedioAmabilidad, promedioClaridad, promedioExigencia);
        String aspectoMejorar = determinarAspectoMejorar(promedioAmabilidad, promedioClaridad, promedioExigencia);
        
        return new ResumenProfesorDTO(
                idProfesor, profesor.getNombreCompleto(), totalResenas.intValue(),
                promedioAmabilidad, promedioClaridad, promedioExigencia, promedioGeneral,
                resenasUtiles.intValue(), resenasNoUtiles.intValue(), porcentajeUtilidad,
                resenasAprobadas.intValue(), resenasPendientes.intValue(), resenasRechazadas.intValue(),
                ultimaResena, mejorAspecto, aspectoMejorar
        );
    }
    
    public ResumenCursoDTO obtenerResumenCurso(Integer idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        
        // Obtener estadísticas básicas
        Long totalResenas = resenaRepository.countByCursoId(idCurso);
        if (totalResenas == 0) {
            return new ResumenCursoDTO(idCurso, curso.getNombreCurso(), curso.getCodigoCurso(), 0,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    0, 0, BigDecimal.ZERO, 0, 0, 0, null, "Sin datos", "Sin datos", 0, curso.getCategoria().name());
        }
        
        // Promedios de calificaciones
        Double avgAmabilidad = resenaRepository.getPromedioAmabilidadByCurso(idCurso);
        Double avgClaridad = resenaRepository.getPromedioClaridadByCurso(idCurso);
        Double avgExigencia = resenaRepository.getPromedioExigenciaByCurso(idCurso);
        
        BigDecimal promedioAmabilidad = avgAmabilidad != null ? BigDecimal.valueOf(avgAmabilidad).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        BigDecimal promedioClaridad = avgClaridad != null ? BigDecimal.valueOf(avgClaridad).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        BigDecimal promedioExigencia = avgExigencia != null ? BigDecimal.valueOf(avgExigencia).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        BigDecimal promedioGeneral = promedioAmabilidad.add(promedioClaridad).add(promedioExigencia).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
        
        // Estadísticas de utilidad
        Long resenasUtiles = resenaRepository.countResenasUtilesByCurso(idCurso);
        Long resenasNoUtiles = resenaRepository.countResenasNoUtilesByCurso(idCurso);
        BigDecimal porcentajeUtilidad = totalResenas > 0 ? 
                BigDecimal.valueOf(resenasUtiles).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(totalResenas), 2, RoundingMode.HALF_UP) : 
                BigDecimal.ZERO;
        
        // Estados de resenas
        Long resenasAprobadas = resenaRepository.countByCursoAndEstado(idCurso, Resena.EstadoResena.APROBADA);
        Long resenasPendientes = resenaRepository.countByCursoAndEstado(idCurso, Resena.EstadoResena.PENDIENTE);
        Long resenasRechazadas = resenaRepository.countByCursoAndEstado(idCurso, Resena.EstadoResena.RECHAZADA);
        
        // Última resena
        LocalDateTime ultimaResena = resenaRepository.getUltimaResenaByCurso(idCurso);
        
        // Profesores del curso (simplificado)
        String profesorMejorEvaluado = "Por implementar";
        String profesorMenosEvaluado = "Por implementar";
        Integer totalProfesores = 1; // Simplificado
        
        return new ResumenCursoDTO(
                idCurso, curso.getNombreCurso(), curso.getCodigoCurso(), totalResenas.intValue(),
                promedioAmabilidad, promedioClaridad, promedioExigencia, promedioGeneral,
                resenasUtiles.intValue(), resenasNoUtiles.intValue(), porcentajeUtilidad,
                resenasAprobadas.intValue(), resenasPendientes.intValue(), resenasRechazadas.intValue(),
                ultimaResena, profesorMejorEvaluado, profesorMenosEvaluado, totalProfesores, curso.getCategoria().name()
        );
    }
    
    public EstadisticasGeneralesDTO obtenerEstadisticasGenerales() {
        // Estadísticas básicas
        Long totalResenas = resenaRepository.countTotalResenas();
        Long totalProfesores = resenaRepository.countDistinctProfesores();
        Long totalCursos = resenaRepository.countDistinctCursos();
        Long totalUsuarios = resenaRepository.countDistinctUsuarios();
        
        // Promedios generales
        Double avgAmabilidad = resenaRepository.getPromedioGeneralAmabilidad();
        Double avgClaridad = resenaRepository.getPromedioGeneralClaridad();
        Double avgExigencia = resenaRepository.getPromedioGeneralExigencia();
        
        BigDecimal promedioAmabilidad = avgAmabilidad != null ? BigDecimal.valueOf(avgAmabilidad).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        BigDecimal promedioClaridad = avgClaridad != null ? BigDecimal.valueOf(avgClaridad).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        BigDecimal promedioExigencia = avgExigencia != null ? BigDecimal.valueOf(avgExigencia).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        BigDecimal promedioUtilidad = promedioAmabilidad.add(promedioClaridad).add(promedioExigencia).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
        
        // Estadísticas de utilidad
        Long resenasUtiles = resenaRepository.countResenasUtilesGenerales();
        Long resenasNoUtiles = resenaRepository.countResenasNoUtilesGenerales();
        BigDecimal porcentajeUtilidad = totalResenas > 0 ? 
                BigDecimal.valueOf(resenasUtiles).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(totalResenas), 2, RoundingMode.HALF_UP) : 
                BigDecimal.ZERO;
        
        // Estados de resenas
        Long resenasAprobadas = resenaRepository.countByEstado(Resena.EstadoResena.APROBADA);
        Long resenasPendientes = resenaRepository.countByEstado(Resena.EstadoResena.PENDIENTE);
        Long resenasRechazadas = resenaRepository.countByEstado(Resena.EstadoResena.RECHAZADA);
        
        // Última resena
        LocalDateTime ultimaResena = resenaRepository.getUltimaResenaGeneral();
        
        // Estadísticas por período
        LocalDateTime inicioMes = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime finMes = LocalDate.now().atTime(23, 59, 59);
        LocalDateTime inicioAno = LocalDate.now().withDayOfYear(1).atStartOfDay();
        LocalDateTime finAno = LocalDate.now().atTime(23, 59, 59);
        
        Long resenasEsteMes = resenaRepository.countByPeriodo(inicioMes, finMes);
        Long resenasEsteAno = resenaRepository.countByPeriodo(inicioAno, finAno);
        
        return new EstadisticasGeneralesDTO(
                totalResenas.intValue(), totalProfesores.intValue(), totalCursos.intValue(), totalUsuarios.intValue(),
                promedioAmabilidad, promedioClaridad, promedioExigencia, promedioUtilidad,
                resenasUtiles.intValue(), resenasNoUtiles.intValue(), porcentajeUtilidad,
                resenasAprobadas.intValue(), resenasPendientes.intValue(), resenasRechazadas.intValue(),
                ultimaResena, "Por implementar", "Por implementar", "Por implementar", 
                resenasEsteMes.intValue(), resenasEsteAno.intValue()
        );
    }
    
    // Métodos auxiliares para análisis
    private String determinarMejorAspecto(BigDecimal amabilidad, BigDecimal claridad, BigDecimal exigencia) {
        if (amabilidad.compareTo(claridad) >= 0 && amabilidad.compareTo(exigencia) >= 0) {
            return "Amabilidad";
        } else if (claridad.compareTo(exigencia) >= 0) {
            return "Claridad";
        } else {
            return "Exigencia";
        }
    }
    
    private String determinarAspectoMejorar(BigDecimal amabilidad, BigDecimal claridad, BigDecimal exigencia) {
        if (amabilidad.compareTo(claridad) <= 0 && amabilidad.compareTo(exigencia) <= 0) {
            return "Amabilidad";
        } else if (claridad.compareTo(exigencia) <= 0) {
            return "Claridad";
        } else {
            return "Exigencia";
        }
    }

    private ResenaDTO convertirADTO(Resena resena) {
        return new ResenaDTO(
                resena.getIdResena(),
                resena.getUsuario().getIdUsuario(),
                resena.getUsuario().getNombreCompleto(),
                resena.getProfesor().getIdProfesor(),
                resena.getProfesor().getNombreCompleto(),
                resena.getCurso().getIdCurso(),
                resena.getCurso().getNombreCurso(),
                resena.getCalifAmabilidad(),
                resena.getCalifClaridad(),
                resena.getCalifExigencia(),
                resena.getComentario(),
                resena.getEsAnonimo(),
                resena.getUtilidad(),
                resena.getEstado().name(),
                resena.getAdminRevisor() != null ? resena.getAdminRevisor().getIdAdmin() : null,
                resena.getAdminRevisor() != null ? resena.getAdminRevisor().getNombreCompleto() : null,
                resena.getFechaPublicacion()
        );
    }
    
    /**
     * Valida que las calificaciones estén entre 1 y 5
     * @param califAmabilidad Calificación de amabilidad
     * @param califClaridad Calificación de claridad
     * @param califExigencia Calificación de exigencia
     * @throws RuntimeException si alguna calificación está fuera del rango 1-5
     */
    private void validarCalificaciones(Integer califAmabilidad, Integer califClaridad, Integer califExigencia) {
        if (califAmabilidad == null || califAmabilidad < 1 || califAmabilidad > 5) {
            throw new RuntimeException("La calificación de amabilidad debe estar entre 1 y 5");
        }
        if (califClaridad == null || califClaridad < 1 || califClaridad > 5) {
            throw new RuntimeException("La calificación de claridad debe estar entre 1 y 5");
        }
        if (califExigencia == null || califExigencia < 1 || califExigencia > 5) {
            throw new RuntimeException("La calificación de exigencia debe estar entre 1 y 5");
        }
    }
}
