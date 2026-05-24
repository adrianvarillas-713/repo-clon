package com.sophialink.sophialink.service;

import com.sophialink.sophialink.dto.ResenaDTO;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private AdministradorRepository administradorRepository;

    @InjectMocks
    private ResenaService resenaService;

    @Test
    void obtenerTodasLasResenas_DeberiaRetornarListaDeResenas() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombreCompleto("Juan Pérez");

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNombreCompleto("Dr. García");

        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombreCurso("Matemáticas I");

        Resena resena = new Resena();
        resena.setIdResena(1);
        resena.setUsuario(usuario);
        resena.setProfesor(profesor);
        resena.setCurso(curso);
        resena.setCalifAmabilidad(5);
        resena.setCalifClaridad(4);
        resena.setCalifExigencia(3);
        resena.setComentario("Excelente profesor");
        resena.setEsAnonimo(false);
        resena.setEstado(Resena.EstadoResena.PENDIENTE);
        resena.setFechaPublicacion(LocalDateTime.now());

        List<Resena> resenas = Arrays.asList(resena);
        when(resenaRepository.findAll()).thenReturn(resenas);

        // Act
        List<ResenaDTO> resultado = resenaService.obtenerTodasLasResenas();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getIdResena());
        assertEquals("Juan Pérez", resultado.get(0).getNombreUsuario());
        assertEquals("Dr. García", resultado.get(0).getNombreProfesor());
        assertEquals("Matemáticas I", resultado.get(0).getNombreCurso());
        assertEquals(5, resultado.get(0).getCalifAmabilidad());
        assertEquals("Excelente profesor", resultado.get(0).getComentario());
        assertEquals("PENDIENTE", resultado.get(0).getEstado());
    }

    @Test
    void obtenerResenaPorId_ConIdValido_DeberiaRetornarResena() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombreCompleto("Juan Pérez");

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNombreCompleto("Dr. García");

        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombreCurso("Matemáticas I");

        Resena resena = new Resena();
        resena.setIdResena(1);
        resena.setUsuario(usuario);
        resena.setProfesor(profesor);
        resena.setCurso(curso);
        resena.setCalifAmabilidad(5);
        resena.setCalifClaridad(4);
        resena.setCalifExigencia(3);
        resena.setComentario("Excelente profesor");
        resena.setEsAnonimo(false);
        resena.setEstado(Resena.EstadoResena.PENDIENTE);
        resena.setFechaPublicacion(LocalDateTime.now());

        when(resenaRepository.findById(1)).thenReturn(Optional.of(resena));

        // Act
        Optional<ResenaDTO> resultado = resenaService.obtenerResenaPorId(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(1, resultado.get().getIdResena());
        assertEquals("Juan Pérez", resultado.get().getNombreUsuario());
        assertEquals("Dr. García", resultado.get().getNombreProfesor());
        assertEquals("Matemáticas I", resultado.get().getNombreCurso());
    }

    @Test
    void obtenerResenaPorId_ConIdInvalido_DeberiaRetornarOptionalVacio() {
        // Arrange
        when(resenaRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<ResenaDTO> resultado = resenaService.obtenerResenaPorId(999);

        // Assert
        assertFalse(resultado.isPresent());
    }

    @Test
    void obtenerResenasPorEstado_ConEstadoValido_DeberiaRetornarListaDeResenas() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombreCompleto("Juan Pérez");

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNombreCompleto("Dr. García");

        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombreCurso("Matemáticas I");

        Resena resena = new Resena();
        resena.setIdResena(1);
        resena.setUsuario(usuario);
        resena.setProfesor(profesor);
        resena.setCurso(curso);
        resena.setCalifAmabilidad(5);
        resena.setCalifClaridad(4);
        resena.setCalifExigencia(3);
        resena.setComentario("Excelente profesor");
        resena.setEsAnonimo(false);
        resena.setEstado(Resena.EstadoResena.PENDIENTE);
        resena.setFechaPublicacion(LocalDateTime.now());

        List<Resena> resenas = Arrays.asList(resena);
        when(resenaRepository.findByEstado(Resena.EstadoResena.PENDIENTE)).thenReturn(resenas);

        // Act
        List<ResenaDTO> resultado = resenaService.obtenerResenasPorEstado("PENDIENTE");

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("PENDIENTE", resultado.get(0).getEstado());
    }

    @Test
    void crearResena_ConDatosValidos_DeberiaRetornarResenaCreada() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombreCompleto("Juan Pérez");

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNombreCompleto("Dr. García");

        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombreCurso("Matemáticas I");

        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdUsuario(1);
        resenaDTO.setIdProfesor(1);
        resenaDTO.setIdCurso(1);
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Excelente profesor");
        resenaDTO.setEsAnonimo(false);

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(profesorRepository.findById(1)).thenReturn(Optional.of(profesor));
        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
        when(resenaRepository.save(any(Resena.class))).thenAnswer(invocation -> {
            Resena resena = invocation.getArgument(0);
            resena.setIdResena(1);
            resena.setFechaPublicacion(LocalDateTime.now());
            return resena;
        });

        // Act
        ResenaDTO resultado = resenaService.crearResena(resenaDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdResena());
        assertEquals("Juan Pérez", resultado.getNombreUsuario());
        assertEquals("Dr. García", resultado.getNombreProfesor());
        assertEquals("Matemáticas I", resultado.getNombreCurso());
        assertEquals(5, resultado.getCalifAmabilidad());
        assertEquals("Excelente profesor", resultado.getComentario());
        assertEquals("PENDIENTE", resultado.getEstado());
        verify(resenaRepository, times(1)).save(any(Resena.class));
    }

    @Test
    void crearResena_ConUsuarioInexistente_DeberiaLanzarExcepcion() {
        // Arrange
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdUsuario(999);
        resenaDTO.setIdProfesor(1);
        resenaDTO.setIdCurso(1);
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Excelente profesor");
        resenaDTO.setEsAnonimo(false);

        when(usuarioRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> resenaService.crearResena(resenaDTO));
    }

    @Test
    void actualizarResena_ConDatosValidos_DeberiaRetornarResenaActualizada() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombreCompleto("Juan Pérez");

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNombreCompleto("Dr. García");

        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombreCurso("Matemáticas I");

        Resena resenaExistente = new Resena();
        resenaExistente.setIdResena(1);
        resenaExistente.setUsuario(usuario);
        resenaExistente.setProfesor(profesor);
        resenaExistente.setCurso(curso);
        resenaExistente.setCalifAmabilidad(5);
        resenaExistente.setCalifClaridad(4);
        resenaExistente.setCalifExigencia(3);
        resenaExistente.setComentario("Excelente profesor");
        resenaExistente.setEsAnonimo(false);
        resenaExistente.setEstado(Resena.EstadoResena.PENDIENTE);
        resenaExistente.setFechaPublicacion(LocalDateTime.now());

        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setCalifAmabilidad(4);
        resenaDTO.setCalifClaridad(3);
        resenaDTO.setCalifExigencia(2);
        resenaDTO.setComentario("Profesor regular");
        resenaDTO.setEsAnonimo(true);

        when(resenaRepository.findById(1)).thenReturn(Optional.of(resenaExistente));
        when(resenaRepository.save(any(Resena.class))).thenReturn(resenaExistente);

        // Act
        Optional<ResenaDTO> resultado = resenaService.actualizarResena(1, resenaDTO);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(4, resultado.get().getCalifAmabilidad());
        assertEquals(3, resultado.get().getCalifClaridad());
        assertEquals(2, resultado.get().getCalifExigencia());
        assertEquals("Profesor regular", resultado.get().getComentario());
        assertEquals(true, resultado.get().getEsAnonimo());
        verify(resenaRepository, times(1)).save(any(Resena.class));
    }

    @Test
    void moderarResena_ConDatosValidos_DeberiaRetornarResenaModerada() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombreCompleto("Juan Pérez");

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNombreCompleto("Dr. García");

        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombreCurso("Matemáticas I");

        Administrador admin = new Administrador();
        admin.setIdAdmin(1);
        admin.setNombreCompleto("Admin Principal");

        Resena resena = new Resena();
        resena.setIdResena(1);
        resena.setUsuario(usuario);
        resena.setProfesor(profesor);
        resena.setCurso(curso);
        resena.setCalifAmabilidad(5);
        resena.setCalifClaridad(4);
        resena.setCalifExigencia(3);
        resena.setComentario("Excelente profesor");
        resena.setEsAnonimo(false);
        resena.setEstado(Resena.EstadoResena.PENDIENTE);
        resena.setFechaPublicacion(LocalDateTime.now());

        when(resenaRepository.findById(1)).thenReturn(Optional.of(resena));
        when(administradorRepository.findById(1)).thenReturn(Optional.of(admin));
        when(resenaRepository.save(any(Resena.class))).thenReturn(resena);

        // Act
        Optional<ResenaDTO> resultado = resenaService.moderarResena(1, "APROBADA", 1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("APROBADA", resultado.get().getEstado());
        assertEquals(1, resultado.get().getIdAdminRevisor());
        assertEquals("Admin Principal", resultado.get().getNombreAdminRevisor());
        verify(resenaRepository, times(1)).save(any(Resena.class));
    }

    @Test
    void eliminarResena_ConIdValido_DeberiaRetornarTrue() {
        // Arrange
        when(resenaRepository.existsById(1)).thenReturn(true);

        // Act
        boolean resultado = resenaService.eliminarResena(1);

        // Assert
        assertTrue(resultado);
        verify(resenaRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminarResena_ConIdInvalido_DeberiaRetornarFalse() {
        // Arrange
        when(resenaRepository.existsById(999)).thenReturn(false);

        // Act
        boolean resultado = resenaService.eliminarResena(999);

        // Assert
        assertFalse(resultado);
        verify(resenaRepository, never()).deleteById(any());
    }
}
