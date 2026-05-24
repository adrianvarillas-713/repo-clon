package com.sophialink.sophialink.repository;

import com.sophialink.sophialink.entity.Resena;
import com.sophialink.sophialink.entity.Usuario;
import com.sophialink.sophialink.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Integer> {
    List<Resena> findByEstado(Resena.EstadoResena estado);
    List<Resena> findByUsuario(Usuario usuario);
    List<Resena> findByProfesor(Profesor profesor);
    List<Resena> findByUtilidad(Boolean utilidad);
    
    // Búsquedas por nombre de profesor
    @Query("SELECT r FROM Resena r JOIN r.profesor p WHERE LOWER(p.nombreCompleto) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Resena> findByProfesorNombreContainingIgnoreCase(@Param("nombre") String nombre);
    
    // Búsquedas por nombre de curso
    @Query("SELECT r FROM Resena r JOIN r.curso c WHERE LOWER(c.nombreCurso) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Resena> findByCursoNombreContainingIgnoreCase(@Param("nombre") String nombre);
    
    // Búsqueda combinada por profesor y curso
    @Query("SELECT r FROM Resena r JOIN r.profesor p JOIN r.curso c WHERE " +
           "LOWER(p.nombreCompleto) LIKE LOWER(CONCAT('%', :nombreProfesor, '%')) AND " +
           "LOWER(c.nombreCurso) LIKE LOWER(CONCAT('%', :nombreCurso, '%'))")
    List<Resena> findByProfesorAndCursoNombreContainingIgnoreCase(@Param("nombreProfesor") String nombreProfesor, 
                                                                 @Param("nombreCurso") String nombreCurso);
    
    // Búsqueda por utilidad y profesor
    @Query("SELECT r FROM Resena r JOIN r.profesor p WHERE r.utilidad = :utilidad AND " +
           "LOWER(p.nombreCompleto) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Resena> findByUtilidadAndProfesorNombreContainingIgnoreCase(@Param("utilidad") Boolean utilidad, 
                                                                     @Param("nombre") String nombre);
    
    // Búsqueda por utilidad y curso
    @Query("SELECT r FROM Resena r JOIN r.curso c WHERE r.utilidad = :utilidad AND " +
           "LOWER(c.nombreCurso) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Resena> findByUtilidadAndCursoNombreContainingIgnoreCase(@Param("utilidad") Boolean utilidad, 
                                                                   @Param("nombre") String nombre);
    
    // ========== MÉTODOS DE ESTADÍSTICAS ==========
    
    // Estadísticas por profesor
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.profesor.idProfesor = :idProfesor")
    Long countByProfesorId(@Param("idProfesor") Integer idProfesor);
    
    @Query("SELECT AVG(r.califAmabilidad) FROM Resena r WHERE r.profesor.idProfesor = :idProfesor AND r.estado = 'APROBADA'")
    Double getPromedioAmabilidadByProfesor(@Param("idProfesor") Integer idProfesor);
    
    @Query("SELECT AVG(r.califClaridad) FROM Resena r WHERE r.profesor.idProfesor = :idProfesor AND r.estado = 'APROBADA'")
    Double getPromedioClaridadByProfesor(@Param("idProfesor") Integer idProfesor);
    
    @Query("SELECT AVG(r.califExigencia) FROM Resena r WHERE r.profesor.idProfesor = :idProfesor AND r.estado = 'APROBADA'")
    Double getPromedioExigenciaByProfesor(@Param("idProfesor") Integer idProfesor);
    
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.profesor.idProfesor = :idProfesor AND r.utilidad = true AND r.estado = 'APROBADA'")
    Long countResenasUtilesByProfesor(@Param("idProfesor") Integer idProfesor);
    
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.profesor.idProfesor = :idProfesor AND r.utilidad = false AND r.estado = 'APROBADA'")
    Long countResenasNoUtilesByProfesor(@Param("idProfesor") Integer idProfesor);
    
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.profesor.idProfesor = :idProfesor AND r.estado = :estado")
    Long countByProfesorAndEstado(@Param("idProfesor") Integer idProfesor, @Param("estado") Resena.EstadoResena estado);
    
    @Query("SELECT MAX(r.fechaPublicacion) FROM Resena r WHERE r.profesor.idProfesor = :idProfesor")
    LocalDateTime getUltimaResenaByProfesor(@Param("idProfesor") Integer idProfesor);
    
    // Estadísticas por curso
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.curso.idCurso = :idCurso")
    Long countByCursoId(@Param("idCurso") Integer idCurso);
    
    @Query("SELECT AVG(r.califAmabilidad) FROM Resena r WHERE r.curso.idCurso = :idCurso AND r.estado = 'APROBADA'")
    Double getPromedioAmabilidadByCurso(@Param("idCurso") Integer idCurso);
    
    @Query("SELECT AVG(r.califClaridad) FROM Resena r WHERE r.curso.idCurso = :idCurso AND r.estado = 'APROBADA'")
    Double getPromedioClaridadByCurso(@Param("idCurso") Integer idCurso);
    
    @Query("SELECT AVG(r.califExigencia) FROM Resena r WHERE r.curso.idCurso = :idCurso AND r.estado = 'APROBADA'")
    Double getPromedioExigenciaByCurso(@Param("idCurso") Integer idCurso);
    
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.curso.idCurso = :idCurso AND r.utilidad = true AND r.estado = 'APROBADA'")
    Long countResenasUtilesByCurso(@Param("idCurso") Integer idCurso);
    
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.curso.idCurso = :idCurso AND r.utilidad = false AND r.estado = 'APROBADA'")
    Long countResenasNoUtilesByCurso(@Param("idCurso") Integer idCurso);
    
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.curso.idCurso = :idCurso AND r.estado = :estado")
    Long countByCursoAndEstado(@Param("idCurso") Integer idCurso, @Param("estado") Resena.EstadoResena estado);
    
    @Query("SELECT MAX(r.fechaPublicacion) FROM Resena r WHERE r.curso.idCurso = :idCurso")
    LocalDateTime getUltimaResenaByCurso(@Param("idCurso") Integer idCurso);
    
    // Estadísticas generales
    @Query("SELECT COUNT(r) FROM Resena r")
    Long countTotalResenas();
    
    @Query("SELECT COUNT(DISTINCT r.profesor) FROM Resena r")
    Long countDistinctProfesores();
    
    @Query("SELECT COUNT(DISTINCT r.curso) FROM Resena r")
    Long countDistinctCursos();
    
    @Query("SELECT COUNT(DISTINCT r.usuario) FROM Resena r")
    Long countDistinctUsuarios();
    
    @Query("SELECT AVG(r.califAmabilidad) FROM Resena r WHERE r.estado = 'APROBADA'")
    Double getPromedioGeneralAmabilidad();
    
    @Query("SELECT AVG(r.califClaridad) FROM Resena r WHERE r.estado = 'APROBADA'")
    Double getPromedioGeneralClaridad();
    
    @Query("SELECT AVG(r.califExigencia) FROM Resena r WHERE r.estado = 'APROBADA'")
    Double getPromedioGeneralExigencia();
    
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.utilidad = true AND r.estado = 'APROBADA'")
    Long countResenasUtilesGenerales();
    
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.utilidad = false AND r.estado = 'APROBADA'")
    Long countResenasNoUtilesGenerales();
    
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.estado = :estado")
    Long countByEstado(@Param("estado") Resena.EstadoResena estado);
    
    @Query("SELECT MAX(r.fechaPublicacion) FROM Resena r")
    LocalDateTime getUltimaResenaGeneral();
    
    // Estadísticas por período
    @Query("SELECT COUNT(r) FROM Resena r WHERE r.fechaPublicacion >= :fechaInicio AND r.fechaPublicacion <= :fechaFin")
    Long countByPeriodo(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
}
