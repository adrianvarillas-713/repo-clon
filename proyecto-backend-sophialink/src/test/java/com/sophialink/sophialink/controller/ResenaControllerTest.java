package com.sophialink.sophialink.controller;

import com.sophialink.sophialink.config.TestSecurityConfig;
import com.sophialink.sophialink.dto.ResenaDTO;
import com.sophialink.sophialink.dto.ResumenProfesorDTO;
import com.sophialink.sophialink.dto.ResumenCursoDTO;
import com.sophialink.sophialink.dto.EstadisticasGeneralesDTO;
import com.sophialink.sophialink.service.ResenaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ResenaController.class)
@Import(TestSecurityConfig.class)
@org.springframework.test.context.ActiveProfiles("test")
class ResenaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResenaService resenaService;

    @Test
    void obtenerTodasLasResenas_DeberiaRetornarListaDeResenas() throws Exception {
        // Arrange
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdResena(1);
        resenaDTO.setIdUsuario(1);
        resenaDTO.setNombreUsuario("Juan Pérez");
        resenaDTO.setIdProfesor(1);
        resenaDTO.setNombreProfesor("Dr. García");
        resenaDTO.setIdCurso(1);
        resenaDTO.setNombreCurso("Matemáticas I");
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Excelente profesor");
        resenaDTO.setEsAnonimo(false);
        resenaDTO.setEstado("PENDIENTE");
        resenaDTO.setFechaPublicacion(LocalDateTime.now());

        List<ResenaDTO> resenas = Arrays.asList(resenaDTO);
        when(resenaService.obtenerTodasLasResenas()).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].idResena").value(1))
                .andExpect(jsonPath("$[0].nombreUsuario").value("Juan Pérez"))
                .andExpect(jsonPath("$[0].nombreProfesor").value("Dr. García"))
                .andExpect(jsonPath("$[0].nombreCurso").value("Matemáticas I"))
                .andExpect(jsonPath("$[0].califAmabilidad").value(5))
                .andExpect(jsonPath("$[0].comentario").value("Excelente profesor"))
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
    }

    @Test
    void obtenerResenaPorId_ConIdValido_DeberiaRetornarResena() throws Exception {
        // Arrange
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdResena(1);
        resenaDTO.setIdUsuario(1);
        resenaDTO.setNombreUsuario("Juan Pérez");
        resenaDTO.setIdProfesor(1);
        resenaDTO.setNombreProfesor("Dr. García");
        resenaDTO.setIdCurso(1);
        resenaDTO.setNombreCurso("Matemáticas I");
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Excelente profesor");
        resenaDTO.setEsAnonimo(false);
        resenaDTO.setEstado("PENDIENTE");
        resenaDTO.setFechaPublicacion(LocalDateTime.now());

        when(resenaService.obtenerResenaPorId(1)).thenReturn(Optional.of(resenaDTO));

        // Act & Assert
        mockMvc.perform(get("/api/resenas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idResena").value(1))
                .andExpect(jsonPath("$.nombreUsuario").value("Juan Pérez"))
                .andExpect(jsonPath("$.nombreProfesor").value("Dr. García"))
                .andExpect(jsonPath("$.nombreCurso").value("Matemáticas I"))
                .andExpect(jsonPath("$.califAmabilidad").value(5))
                .andExpect(jsonPath("$.comentario").value("Excelente profesor"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    void obtenerResenaPorId_ConIdInvalido_DeberiaRetornar404() throws Exception {
        // Arrange
        when(resenaService.obtenerResenaPorId(999)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/resenas/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerResenasPorEstado_ConEstadoValido_DeberiaRetornarListaDeResenas() throws Exception {
        // Arrange
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdResena(1);
        resenaDTO.setIdUsuario(1);
        resenaDTO.setNombreUsuario("Juan Pérez");
        resenaDTO.setIdProfesor(1);
        resenaDTO.setNombreProfesor("Dr. García");
        resenaDTO.setIdCurso(1);
        resenaDTO.setNombreCurso("Matemáticas I");
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Excelente profesor");
        resenaDTO.setEsAnonimo(false);
        resenaDTO.setEstado("PENDIENTE");
        resenaDTO.setFechaPublicacion(LocalDateTime.now());

        List<ResenaDTO> resenas = Arrays.asList(resenaDTO);
        when(resenaService.obtenerResenasPorEstado("PENDIENTE")).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/estado/PENDIENTE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
    }

    @Test
    void obtenerResenasPorProfesor_ConIdValido_DeberiaRetornarListaDeResenas() throws Exception {
        // Arrange
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdResena(1);
        resenaDTO.setIdUsuario(1);
        resenaDTO.setNombreUsuario("Juan Pérez");
        resenaDTO.setIdProfesor(1);
        resenaDTO.setNombreProfesor("Dr. García");
        resenaDTO.setIdCurso(1);
        resenaDTO.setNombreCurso("Matemáticas I");
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Excelente profesor");
        resenaDTO.setEsAnonimo(false);
        resenaDTO.setEstado("PENDIENTE");
        resenaDTO.setFechaPublicacion(LocalDateTime.now());

        List<ResenaDTO> resenas = Arrays.asList(resenaDTO);
        when(resenaService.obtenerResenasPorProfesor(1)).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/profesor/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].idProfesor").value(1))
                .andExpect(jsonPath("$[0].nombreProfesor").value("Dr. García"));
    }

    @Test
    void obtenerResenasPorUsuario_ConIdValido_DeberiaRetornarListaDeResenas() throws Exception {
        // Arrange
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdResena(1);
        resenaDTO.setIdUsuario(1);
        resenaDTO.setNombreUsuario("Juan Pérez");
        resenaDTO.setIdProfesor(1);
        resenaDTO.setNombreProfesor("Dr. García");
        resenaDTO.setIdCurso(1);
        resenaDTO.setNombreCurso("Matemáticas I");
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Excelente profesor");
        resenaDTO.setEsAnonimo(false);
        resenaDTO.setEstado("PENDIENTE");
        resenaDTO.setFechaPublicacion(LocalDateTime.now());

        List<ResenaDTO> resenas = Arrays.asList(resenaDTO);
        when(resenaService.obtenerResenasPorUsuario(1)).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].idUsuario").value(1))
                .andExpect(jsonPath("$[0].nombreUsuario").value("Juan Pérez"));
    }

    @Test
    void crearResena_ConDatosValidos_DeberiaRetornarResenaCreada() throws Exception {
        // Arrange
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdUsuario(1);
        resenaDTO.setIdProfesor(1);
        resenaDTO.setIdCurso(1);
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Excelente profesor");
        resenaDTO.setEsAnonimo(false);

        ResenaDTO resenaCreada = new ResenaDTO();
        resenaCreada.setIdResena(1);
        resenaCreada.setIdUsuario(1);
        resenaCreada.setNombreUsuario("Juan Pérez");
        resenaCreada.setIdProfesor(1);
        resenaCreada.setNombreProfesor("Dr. García");
        resenaCreada.setIdCurso(1);
        resenaCreada.setNombreCurso("Matemáticas I");
        resenaCreada.setCalifAmabilidad(5);
        resenaCreada.setCalifClaridad(4);
        resenaCreada.setCalifExigencia(3);
        resenaCreada.setComentario("Excelente profesor");
        resenaCreada.setEsAnonimo(false);
        resenaCreada.setEstado("PENDIENTE");
        resenaCreada.setFechaPublicacion(LocalDateTime.now());

        when(resenaService.crearResena(any(ResenaDTO.class))).thenReturn(resenaCreada);

        // Act & Assert
        mockMvc.perform(post("/api/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idUsuario\": 1,\n" +
                        "  \"idProfesor\": 1,\n" +
                        "  \"idCurso\": 1,\n" +
                        "  \"califAmabilidad\": 5,\n" +
                        "  \"califClaridad\": 4,\n" +
                        "  \"califExigencia\": 3,\n" +
                        "  \"comentario\": \"Excelente profesor\",\n" +
                        "  \"esAnonimo\": false\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idResena").value(1))
                .andExpect(jsonPath("$.nombreUsuario").value("Juan Pérez"))
                .andExpect(jsonPath("$.nombreProfesor").value("Dr. García"))
                .andExpect(jsonPath("$.nombreCurso").value("Matemáticas I"))
                .andExpect(jsonPath("$.califAmabilidad").value(5))
                .andExpect(jsonPath("$.comentario").value("Excelente profesor"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    void actualizarResena_ConDatosValidos_DeberiaRetornarResenaActualizada() throws Exception {
        // Arrange
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setCalifAmabilidad(4);
        resenaDTO.setCalifClaridad(3);
        resenaDTO.setCalifExigencia(2);
        resenaDTO.setComentario("Profesor regular");
        resenaDTO.setEsAnonimo(true);

        ResenaDTO resenaActualizada = new ResenaDTO();
        resenaActualizada.setIdResena(1);
        resenaActualizada.setIdUsuario(1);
        resenaActualizada.setNombreUsuario("Juan Pérez");
        resenaActualizada.setIdProfesor(1);
        resenaActualizada.setNombreProfesor("Dr. García");
        resenaActualizada.setIdCurso(1);
        resenaActualizada.setNombreCurso("Matemáticas I");
        resenaActualizada.setCalifAmabilidad(4);
        resenaActualizada.setCalifClaridad(3);
        resenaActualizada.setCalifExigencia(2);
        resenaActualizada.setComentario("Profesor regular");
        resenaActualizada.setEsAnonimo(true);
        resenaActualizada.setEstado("PENDIENTE");
        resenaActualizada.setFechaPublicacion(LocalDateTime.now());

        when(resenaService.actualizarResena(eq(1), any(ResenaDTO.class))).thenReturn(Optional.of(resenaActualizada));

        // Act & Assert
        mockMvc.perform(put("/api/resenas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"califAmabilidad\": 4,\n" +
                        "  \"califClaridad\": 3,\n" +
                        "  \"califExigencia\": 2,\n" +
                        "  \"comentario\": \"Profesor regular\",\n" +
                        "  \"esAnonimo\": true\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idResena").value(1))
                .andExpect(jsonPath("$.califAmabilidad").value(4))
                .andExpect(jsonPath("$.califClaridad").value(3))
                .andExpect(jsonPath("$.califExigencia").value(2))
                .andExpect(jsonPath("$.comentario").value("Profesor regular"))
                .andExpect(jsonPath("$.esAnonimo").value(true));
    }

    @Test
    void moderarResena_ConDatosValidos_DeberiaRetornarResenaModerada() throws Exception {
        // Arrange
        ResenaDTO resenaModerada = new ResenaDTO();
        resenaModerada.setIdResena(1);
        resenaModerada.setIdUsuario(1);
        resenaModerada.setNombreUsuario("Juan Pérez");
        resenaModerada.setIdProfesor(1);
        resenaModerada.setNombreProfesor("Dr. García");
        resenaModerada.setIdCurso(1);
        resenaModerada.setNombreCurso("Matemáticas I");
        resenaModerada.setCalifAmabilidad(5);
        resenaModerada.setCalifClaridad(4);
        resenaModerada.setCalifExigencia(3);
        resenaModerada.setComentario("Excelente profesor");
        resenaModerada.setEsAnonimo(false);
        resenaModerada.setEstado("APROBADA");
        resenaModerada.setIdAdminRevisor(1);
        resenaModerada.setNombreAdminRevisor("Admin Principal");
        resenaModerada.setFechaPublicacion(LocalDateTime.now());

        when(resenaService.moderarResena(1, "APROBADA", 1)).thenReturn(Optional.of(resenaModerada));

        // Act & Assert
        mockMvc.perform(put("/api/resenas/1/moderar")
                .param("estado", "APROBADA")
                .param("idAdmin", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idResena").value(1))
                .andExpect(jsonPath("$.estado").value("APROBADA"))
                .andExpect(jsonPath("$.idAdminRevisor").value(1))
                .andExpect(jsonPath("$.nombreAdminRevisor").value("Admin Principal"));
    }

    @Test
    void eliminarResena_ConIdValido_DeberiaRetornar204() throws Exception {
        // Arrange
        when(resenaService.eliminarResena(1)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/resenas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarResena_ConIdInvalido_DeberiaRetornar404() throws Exception {
        // Arrange
        when(resenaService.eliminarResena(999)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/api/resenas/999"))
                .andExpect(status().isNotFound());
    }

    // ========== TESTS DE BÚSQUEDA ==========

    @Test
    void buscarResenas_SinParametros_DeberiaRetornarTodasLasResenas() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1), crearResenaDTO(2));
        when(resenaService.obtenerTodasLasResenas()).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idResena", is(1)))
                .andExpect(jsonPath("$[1].idResena", is(2)));
    }

    @Test
    void buscarResenas_PorProfesor_DeberiaRetornarResenasDelProfesor() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorProfesor("Juan")).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar")
                .param("profesor", "Juan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idResena", is(1)));
    }

    @Test
    void buscarResenas_PorCurso_DeberiaRetornarResenasDelCurso() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorCurso("Matemáticas")).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar")
                .param("curso", "Matemáticas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idResena", is(1)));
    }

    @Test
    void buscarResenas_PorUtilidad_DeberiaRetornarResenasConUtilidad() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorUtilidad(true)).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar")
                .param("utilidad", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idResena", is(1)));
    }

    @Test
    void buscarResenas_PorProfesorYCurso_DeberiaRetornarResenasCombinadas() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorProfesorYCurso("Juan", "Matemáticas")).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar")
                .param("profesor", "Juan")
                .param("curso", "Matemáticas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idResena", is(1)));
    }

    @Test
    void buscarPorProfesor_ConNombreValido_DeberiaRetornarResenas() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorProfesor("Juan")).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/profesor/Juan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idResena", is(1)));
    }

    @Test
    void buscarPorCurso_ConNombreValido_DeberiaRetornarResenas() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorCurso("Matemáticas")).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/curso/Matemáticas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idResena", is(1)));
    }

    @Test
    void buscarPorUtilidad_ConValorValido_DeberiaRetornarResenas() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorUtilidad(true)).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/utilidad/true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idResena", is(1)));
    }

    @Test
    void buscarPorUtilidadYProfesor_ConParametrosValidos_DeberiaRetornarResenas() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorUtilidadYProfesor(true, "Juan")).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/utilidad/true/profesor/Juan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idResena", is(1)));
    }

    @Test
    void buscarPorUtilidadYCurso_ConParametrosValidos_DeberiaRetornarResenas() throws Exception {
        // Arrange
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorUtilidadYCurso(true, "Matemáticas")).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/utilidad/true/curso/Matemáticas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idResena", is(1)));
    }

    // ========== TESTS COMPLEJOS Y DIFÍCILES ==========

    @Test
    void crearResena_ConDatosInvalidos_DeberiaRetornarBadRequest() throws Exception {
        // Arrange - Datos inválidos (calificaciones fuera de rango)
        String jsonContent = """
            {
                "idUsuario": 1,
                "idProfesor": 1,
                "idCurso": 1,
                "califAmabilidad": 6,
                "califClaridad": 0,
                "califExigencia": -1,
                "comentario": "Test inválido",
                "esAnonimo": false,
                "utilidad": true
            }
            """;

        when(resenaService.crearResena(any(ResenaDTO.class)))
                .thenThrow(new RuntimeException("Datos inválidos"));

        // Act & Assert
        mockMvc.perform(post("/api/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    void buscarResenas_ConParametrosComplejos_DeberiaManejarCorrectamente() throws Exception {
        // Arrange - Búsqueda compleja con múltiples parámetros
        List<ResenaDTO> resenas = Arrays.asList(
                crearResenaDTOCompleja(1, "Juan Pérez", "Matemáticas Avanzadas", true),
                crearResenaDTOCompleja(2, "Juan Pérez", "Matemáticas Básicas", false)
        );
        
        when(resenaService.buscarPorProfesorYCurso("Juan", "Matemáticas"))
                .thenReturn(resenas);

        // Act & Assert - Búsqueda con caracteres especiales y espacios
        mockMvc.perform(get("/api/resenas/buscar")
                .param("profesor", "Juan")
                .param("curso", "Matemáticas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombreProfesor", is("Juan Pérez")))
                .andExpect(jsonPath("$[0].nombreCurso", is("Matemáticas Avanzadas")))
                .andExpect(jsonPath("$[0].utilidad", is(true)))
                .andExpect(jsonPath("$[1].utilidad", is(false)));
    }

    @Test
    void buscarResenas_ConCaracteresEspeciales_DeberiaFuncionarCorrectamente() throws Exception {
        // Arrange - Búsqueda con caracteres especiales
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorProfesor("José María")).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/profesor/José María"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void buscarResenas_ConTextoLargo_DeberiaManejarCorrectamente() throws Exception {
        // Arrange - Búsqueda con texto muy largo
        String nombreLargo = "Profesor con nombre muy largo que podría causar problemas en la base de datos si no se maneja correctamente";
        List<ResenaDTO> resenas = Arrays.asList(crearResenaDTO(1));
        when(resenaService.buscarPorProfesor(nombreLargo)).thenReturn(resenas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/profesor/{nombre}", nombreLargo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void actualizarResena_ConDatosParciales_DeberiaManejarCorrectamente() throws Exception {
        // Arrange - Actualización parcial
        ResenaDTO resenaExistente = crearResenaDTO(1);
        ResenaDTO resenaActualizada = crearResenaDTO(1);
        resenaActualizada.setComentario("Comentario actualizado");
        resenaActualizada.setUtilidad(false);

        when(resenaService.obtenerResenaPorId(1)).thenReturn(Optional.of(resenaExistente));
        when(resenaService.actualizarResena(eq(1), any(ResenaDTO.class)))
                .thenReturn(Optional.of(resenaActualizada));

        String jsonContent = """
            {
                "idResena": 1,
                "comentario": "Comentario actualizado",
                "utilidad": false
            }
            """;

        // Act & Assert
        mockMvc.perform(put("/api/resenas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comentario", is("Comentario actualizado")))
                .andExpect(jsonPath("$.utilidad", is(false)));
    }

    @Test
    void buscarResenas_ConResultadosVacios_DeberiaRetornarListaVacia() throws Exception {
        // Arrange - Búsqueda que no encuentra resultados
        when(resenaService.buscarPorProfesor("ProfesorInexistente")).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/profesor/ProfesorInexistente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void buscarResenas_ConUtilidadYProfesor_DeberiaFiltrarCorrectamente() throws Exception {
        // Arrange - Búsqueda compleja por utilidad y profesor
        List<ResenaDTO> resenasUtiles = Arrays.asList(
                crearResenaDTOCompleja(1, "María García", "Física", true),
                crearResenaDTOCompleja(2, "María García", "Química", true)
        );
        
        when(resenaService.buscarPorUtilidadYProfesor(true, "María"))
                .thenReturn(resenasUtiles);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/utilidad/true/profesor/María"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].utilidad", is(true)))
                .andExpect(jsonPath("$[1].utilidad", is(true)))
                .andExpect(jsonPath("$[0].nombreProfesor", containsString("María")))
                .andExpect(jsonPath("$[1].nombreProfesor", containsString("María")));
    }

    @Test
    void moderarResena_ConEstadosInvalidos_DeberiaManejarErrores() throws Exception {
        // Arrange - Moderación con estado inválido
        when(resenaService.moderarResena(1, "ESTADO_INVALIDO", 1))
                .thenThrow(new RuntimeException("Estado inválido"));

        // Act & Assert
        mockMvc.perform(put("/api/resenas/1/moderar")
                .param("estado", "ESTADO_INVALIDO")
                .param("idAdmin", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void buscarResenas_ConParametrosNulos_DeberiaManejarCorrectamente() throws Exception {
        // Arrange - Búsqueda con parámetros nulos
        List<ResenaDTO> todasLasResenas = Arrays.asList(crearResenaDTO(1), crearResenaDTO(2));
        when(resenaService.obtenerTodasLasResenas()).thenReturn(todasLasResenas);

        // Act & Assert - Sin parámetros, debería retornar todas
        mockMvc.perform(get("/api/resenas/buscar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void crearResena_ConRelacionesComplejas_DeberiaFuncionarCorrectamente() throws Exception {
        // Arrange - Reseña con datos complejos
        ResenaDTO resenaCompleja = crearResenaDTOCompleja(1, "Dr. Juan Carlos Pérez", "Matemáticas Avanzadas", true);
        resenaCompleja.setComentario("Excelente profesor, muy claro en sus explicaciones y siempre disponible para dudas. El curso fue muy útil para mi carrera.");
        resenaCompleja.setEsAnonimo(true);
        
        when(resenaService.crearResena(any(ResenaDTO.class))).thenReturn(resenaCompleja);

        String jsonContent = """
            {
                "idUsuario": 1,
                "idProfesor": 1,
                "idCurso": 1,
                "califAmabilidad": 5,
                "califClaridad": 5,
                "califExigencia": 4,
                "comentario": "Excelente profesor, muy claro en sus explicaciones y siempre disponible para dudas. El curso fue muy útil para mi carrera.",
                "esAnonimo": true,
                "utilidad": true
            }
            """;

        // Act & Assert
        mockMvc.perform(post("/api/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.comentario", is("Excelente profesor, muy claro en sus explicaciones y siempre disponible para dudas. El curso fue muy útil para mi carrera.")))
                .andExpect(jsonPath("$.esAnonimo", is(true)))
                .andExpect(jsonPath("$.utilidad", is(true)));
    }

    @Test
    void buscarResenas_ConFiltrosMultiples_DeberiaAplicarTodosLosFiltros() throws Exception {
        // Arrange - Búsqueda con múltiples filtros
        List<ResenaDTO> resenasFiltradas = Arrays.asList(crearResenaDTOCompleja(1, "Ana López", "Biología", true));
        
        when(resenaService.buscarPorUtilidadYCurso(true, "Biología"))
                .thenReturn(resenasFiltradas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/buscar/utilidad/true/curso/Biología"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].utilidad", is(true)))
                .andExpect(jsonPath("$[0].nombreCurso", is("Biología")))
                .andExpect(jsonPath("$[0].nombreProfesor", is("Ana López")));
    }

    // ========== TESTS DE REPORTES Y ESTADÍSTICAS ==========

    @Test
    void obtenerResumenProfesor_ConProfesorExistente_DeberiaRetornarResumen() throws Exception {
        // Arrange
        ResumenProfesorDTO resumen = crearResumenProfesorDTO(1, "Dr. Juan Pérez", 15, 4.5, 4.2, 3.8);
        when(resenaService.obtenerResumenProfesor(1)).thenReturn(resumen);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/resumen/profesor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProfesor", is(1)))
                .andExpect(jsonPath("$.nombreProfesor", is("Dr. Juan Pérez")))
                .andExpect(jsonPath("$.totalResenas", is(15)))
                .andExpect(jsonPath("$.promedioAmabilidad", is(4.5)))
                .andExpect(jsonPath("$.promedioClaridad", is(4.2)))
                .andExpect(jsonPath("$.promedioExigencia", is(3.8)))
                .andExpect(jsonPath("$.mejorAspecto", is("Amabilidad")))
                .andExpect(jsonPath("$.aspectoMejorar", is("Exigencia")));
    }

    @Test
    void obtenerResumenProfesor_ConProfesorInexistente_DeberiaRetornarNotFound() throws Exception {
        // Arrange
        when(resenaService.obtenerResumenProfesor(999))
                .thenThrow(new RuntimeException("Profesor no encontrado"));

        // Act & Assert
        mockMvc.perform(get("/api/resenas/resumen/profesor/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerResumenCurso_ConCursoExistente_DeberiaRetornarResumen() throws Exception {
        // Arrange
        ResumenCursoDTO resumen = crearResumenCursoDTO(1, "Matemáticas Avanzadas", "MAT-301", 25, 4.2, 4.0, 3.9);
        when(resenaService.obtenerResumenCurso(1)).thenReturn(resumen);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/resumen/curso/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCurso", is(1)))
                .andExpect(jsonPath("$.nombreCurso", is("Matemáticas Avanzadas")))
                .andExpect(jsonPath("$.codigoCurso", is("MAT-301")))
                .andExpect(jsonPath("$.totalResenas", is(25)))
                .andExpect(jsonPath("$.promedioAmabilidad", is(4.2)))
                .andExpect(jsonPath("$.promedioClaridad", is(4.0)))
                .andExpect(jsonPath("$.promedioExigencia", is(3.9)))
                .andExpect(jsonPath("$.categoria", is("INGENIERIA")));
    }

    @Test
    void obtenerResumenCurso_ConCursoInexistente_DeberiaRetornarNotFound() throws Exception {
        // Arrange
        when(resenaService.obtenerResumenCurso(999))
                .thenThrow(new RuntimeException("Curso no encontrado"));

        // Act & Assert
        mockMvc.perform(get("/api/resenas/resumen/curso/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerEstadisticasGenerales_DeberiaRetornarEstadisticas() throws Exception {
        // Arrange
        EstadisticasGeneralesDTO estadisticas = crearEstadisticasGeneralesDTO();
        when(resenaService.obtenerEstadisticasGenerales()).thenReturn(estadisticas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/estadisticas/generales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalResenas", is(150)))
                .andExpect(jsonPath("$.totalProfesores", is(25)))
                .andExpect(jsonPath("$.totalCursos", is(30)))
                .andExpect(jsonPath("$.totalUsuarios", is(200)))
                .andExpect(jsonPath("$.promedioGeneralAmabilidad", is(4.1)))
                .andExpect(jsonPath("$.promedioGeneralClaridad", is(3.9)))
                .andExpect(jsonPath("$.promedioGeneralExigencia", is(3.7)))
                .andExpect(jsonPath("$.resenasUtiles", is(120)))
                .andExpect(jsonPath("$.resenasNoUtiles", is(30)))
                .andExpect(jsonPath("$.porcentajeUtilidad", is(80.0)))
                .andExpect(jsonPath("$.resenasAprobadas", is(120)))
                .andExpect(jsonPath("$.resenasPendientes", is(20)))
                .andExpect(jsonPath("$.resenasRechazadas", is(10)));
    }

    @Test
    void obtenerPromedioProfesor_ConProfesorExistente_DeberiaRetornarPromedio() throws Exception {
        // Arrange
        ResumenProfesorDTO resumen = crearResumenProfesorDTO(1, "Dr. Juan Pérez", 15, 4.5, 4.2, 3.8);
        when(resenaService.obtenerResumenProfesor(1)).thenReturn(resumen);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/estadisticas/profesor/1/promedio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(4.17))); // Promedio redondeado a 2 decimales
    }

    @Test
    void obtenerPromedioCurso_ConCursoExistente_DeberiaRetornarPromedio() throws Exception {
        // Arrange
        ResumenCursoDTO resumen = crearResumenCursoDTO(1, "Matemáticas Avanzadas", "MAT-301", 25, 4.2, 4.0, 3.9);
        when(resenaService.obtenerResumenCurso(1)).thenReturn(resumen);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/estadisticas/curso/1/promedio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(4.03))); // Promedio redondeado a 2 decimales
    }

    @Test
    void obtenerEstadisticasUtilidad_DeberiaRetornarEstadisticasUtilidad() throws Exception {
        // Arrange
        EstadisticasGeneralesDTO estadisticas = crearEstadisticasGeneralesDTO();
        when(resenaService.obtenerEstadisticasGenerales()).thenReturn(estadisticas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/estadisticas/utilidad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resenasUtiles", is(120)))
                .andExpect(jsonPath("$.resenasNoUtiles", is(30)))
                .andExpect(jsonPath("$.porcentajeUtilidad", is(80.0)));
    }

    @Test
    void obtenerEstadisticasEstados_DeberiaRetornarEstadisticasEstados() throws Exception {
        // Arrange
        EstadisticasGeneralesDTO estadisticas = crearEstadisticasGeneralesDTO();
        when(resenaService.obtenerEstadisticasGenerales()).thenReturn(estadisticas);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/estadisticas/estados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resenasAprobadas", is(120)))
                .andExpect(jsonPath("$.resenasPendientes", is(20)))
                .andExpect(jsonPath("$.resenasRechazadas", is(10)));
    }

    @Test
    void obtenerResumenProfesor_ConProfesorSinResenas_DeberiaRetornarResumenVacio() throws Exception {
        // Arrange
        ResumenProfesorDTO resumenVacio = new ResumenProfesorDTO();
        resumenVacio.setIdProfesor(1);
        resumenVacio.setNombreProfesor("Profesor Nuevo");
        resumenVacio.setTotalResenas(0);
        resumenVacio.setPromedioAmabilidad(BigDecimal.ZERO);
        resumenVacio.setPromedioClaridad(BigDecimal.ZERO);
        resumenVacio.setPromedioExigencia(BigDecimal.ZERO);
        resumenVacio.setPromedioGeneral(BigDecimal.ZERO);
        resumenVacio.setMejorAspecto("Sin datos");
        resumenVacio.setAspectoMejorar("Sin datos");
        
        when(resenaService.obtenerResumenProfesor(1)).thenReturn(resumenVacio);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/resumen/profesor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalResenas", is(0)))
                .andExpect(jsonPath("$.promedioGeneral", is(0)))
                .andExpect(jsonPath("$.mejorAspecto", is("Sin datos")));
    }

    @Test
    void obtenerResumenCurso_ConCursoSinResenas_DeberiaRetornarResumenVacio() throws Exception {
        // Arrange
        ResumenCursoDTO resumenVacio = new ResumenCursoDTO();
        resumenVacio.setIdCurso(1);
        resumenVacio.setNombreCurso("Curso Nuevo");
        resumenVacio.setCodigoCurso("NEW-101");
        resumenVacio.setTotalResenas(0);
        resumenVacio.setPromedioAmabilidad(BigDecimal.ZERO);
        resumenVacio.setPromedioClaridad(BigDecimal.ZERO);
        resumenVacio.setPromedioExigencia(BigDecimal.ZERO);
        resumenVacio.setPromedioGeneral(BigDecimal.ZERO);
        resumenVacio.setCategoria("INGENIERIA");
        
        when(resenaService.obtenerResumenCurso(1)).thenReturn(resumenVacio);

        // Act & Assert
        mockMvc.perform(get("/api/resenas/resumen/curso/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalResenas", is(0)))
                .andExpect(jsonPath("$.promedioGeneral", is(0)))
                .andExpect(jsonPath("$.categoria", is("INGENIERIA")));
    }

    // Métodos auxiliares para crear DTOs de reportes
    private ResumenProfesorDTO crearResumenProfesorDTO(Integer id, String nombre, Integer totalResenas, 
                                                       Double amabilidad, Double claridad, Double exigencia) {
        ResumenProfesorDTO resumen = new ResumenProfesorDTO();
        resumen.setIdProfesor(id);
        resumen.setNombreProfesor(nombre);
        resumen.setTotalResenas(totalResenas);
        resumen.setPromedioAmabilidad(BigDecimal.valueOf(amabilidad));
        resumen.setPromedioClaridad(BigDecimal.valueOf(claridad));
        resumen.setPromedioExigencia(BigDecimal.valueOf(exigencia));
        resumen.setPromedioGeneral(BigDecimal.valueOf((amabilidad + claridad + exigencia) / 3.0));
        resumen.setResenasUtiles(12);
        resumen.setResenasNoUtiles(3);
        resumen.setPorcentajeUtilidad(BigDecimal.valueOf(80.0));
        resumen.setResenasAprobadas(10);
        resumen.setResenasPendientes(3);
        resumen.setResenasRechazadas(2);
        resumen.setMejorAspecto("Amabilidad");
        resumen.setAspectoMejorar("Exigencia");
        return resumen;
    }

    private ResumenCursoDTO crearResumenCursoDTO(Integer id, String nombre, String codigo, Integer totalResenas,
                                                 Double amabilidad, Double claridad, Double exigencia) {
        ResumenCursoDTO resumen = new ResumenCursoDTO();
        resumen.setIdCurso(id);
        resumen.setNombreCurso(nombre);
        resumen.setCodigoCurso(codigo);
        resumen.setTotalResenas(totalResenas);
        resumen.setPromedioAmabilidad(BigDecimal.valueOf(amabilidad));
        resumen.setPromedioClaridad(BigDecimal.valueOf(claridad));
        resumen.setPromedioExigencia(BigDecimal.valueOf(exigencia));
        resumen.setPromedioGeneral(BigDecimal.valueOf((amabilidad + claridad + exigencia) / 3.0));
        resumen.setResenasUtiles(20);
        resumen.setResenasNoUtiles(5);
        resumen.setPorcentajeUtilidad(BigDecimal.valueOf(80.0));
        resumen.setResenasAprobadas(18);
        resumen.setResenasPendientes(5);
        resumen.setResenasRechazadas(2);
        resumen.setCategoria("INGENIERIA");
        return resumen;
    }

    private EstadisticasGeneralesDTO crearEstadisticasGeneralesDTO() {
        EstadisticasGeneralesDTO estadisticas = new EstadisticasGeneralesDTO();
        estadisticas.setTotalResenas(150);
        estadisticas.setTotalProfesores(25);
        estadisticas.setTotalCursos(30);
        estadisticas.setTotalUsuarios(200);
        estadisticas.setPromedioGeneralAmabilidad(BigDecimal.valueOf(4.1));
        estadisticas.setPromedioGeneralClaridad(BigDecimal.valueOf(3.9));
        estadisticas.setPromedioGeneralExigencia(BigDecimal.valueOf(3.7));
        estadisticas.setPromedioGeneralUtilidad(BigDecimal.valueOf(3.9));
        estadisticas.setResenasUtiles(120);
        estadisticas.setResenasNoUtiles(30);
        estadisticas.setPorcentajeUtilidad(BigDecimal.valueOf(80.0));
        estadisticas.setResenasAprobadas(120);
        estadisticas.setResenasPendientes(20);
        estadisticas.setResenasRechazadas(10);
        estadisticas.setResenasEsteMes(15);
        estadisticas.setResenasEsteAno(150);
        return estadisticas;
    }

    // Métodos auxiliares para crear DTOs de prueba complejos

    private ResenaDTO crearResenaDTOCompleja(Integer id, String nombreProfesor, String nombreCurso, Boolean utilidad) {
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdResena(id);
        resenaDTO.setIdUsuario(1);
        resenaDTO.setNombreUsuario("Usuario Test");
        resenaDTO.setIdProfesor(1);
        resenaDTO.setNombreProfesor(nombreProfesor);
        resenaDTO.setIdCurso(1);
        resenaDTO.setNombreCurso(nombreCurso);
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Comentario de prueba");
        resenaDTO.setEsAnonimo(false);
        resenaDTO.setUtilidad(utilidad);
        resenaDTO.setEstado("APROBADA");
        resenaDTO.setFechaPublicacion(LocalDateTime.now());
        return resenaDTO;
    }

    // Método auxiliar original para crear ResenaDTO de prueba
    private ResenaDTO crearResenaDTO(Integer id) {
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setIdResena(id);
        resenaDTO.setIdUsuario(1);
        resenaDTO.setNombreUsuario("Usuario Test");
        resenaDTO.setIdProfesor(1);
        resenaDTO.setNombreProfesor("Profesor Test");
        resenaDTO.setIdCurso(1);
        resenaDTO.setNombreCurso("Curso Test");
        resenaDTO.setCalifAmabilidad(5);
        resenaDTO.setCalifClaridad(4);
        resenaDTO.setCalifExigencia(3);
        resenaDTO.setComentario("Comentario test");
        resenaDTO.setEsAnonimo(false);
        resenaDTO.setUtilidad(true);
        resenaDTO.setEstado("APROBADA");
        resenaDTO.setFechaPublicacion(LocalDateTime.now());
        return resenaDTO;
    }
    
    // ========== TESTS DE VALIDACIÓN DE CALIFICACIONES ==========
    
    @Test
    void crearResena_ConCalificacionAmabilidadInvalida_DeberiaRetornarBadRequest() throws Exception {
        String jsonContent = """
            {
                "idUsuario": 1,
                "idProfesor": 1,
                "idCurso": 1,
                "califAmabilidad": 6,
                "califClaridad": 4,
                "califExigencia": 3,
                "comentario": "Test con calificación inválida",
                "esAnonimo": false,
                "utilidad": true
            }
            """;

        when(resenaService.crearResena(any(ResenaDTO.class)))
                .thenThrow(new RuntimeException("La calificación de amabilidad debe estar entre 1 y 5"));

        mockMvc.perform(post("/api/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void crearResena_ConCalificacionClaridadInvalida_DeberiaRetornarBadRequest() throws Exception {
        String jsonContent = """
            {
                "idUsuario": 1,
                "idProfesor": 1,
                "idCurso": 1,
                "califAmabilidad": 4,
                "califClaridad": 0,
                "califExigencia": 3,
                "comentario": "Test con calificación inválida",
                "esAnonimo": false,
                "utilidad": true
            }
            """;

        when(resenaService.crearResena(any(ResenaDTO.class)))
                .thenThrow(new RuntimeException("La calificación de claridad debe estar entre 1 y 5"));

        mockMvc.perform(post("/api/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void crearResena_ConCalificacionExigenciaInvalida_DeberiaRetornarBadRequest() throws Exception {
        String jsonContent = """
            {
                "idUsuario": 1,
                "idProfesor": 1,
                "idCurso": 1,
                "califAmabilidad": 4,
                "califClaridad": 4,
                "califExigencia": -1,
                "comentario": "Test con calificación inválida",
                "esAnonimo": false,
                "utilidad": true
            }
            """;

        when(resenaService.crearResena(any(ResenaDTO.class)))
                .thenThrow(new RuntimeException("La calificación de exigencia debe estar entre 1 y 5"));

        mockMvc.perform(post("/api/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void crearResena_ConCalificacionesNulas_DeberiaRetornarBadRequest() throws Exception {
        String jsonContent = """
            {
                "idUsuario": 1,
                "idProfesor": 1,
                "idCurso": 1,
                "califAmabilidad": null,
                "califClaridad": null,
                "califExigencia": null,
                "comentario": "Test con calificaciones nulas",
                "esAnonimo": false,
                "utilidad": true
            }
            """;

        when(resenaService.crearResena(any(ResenaDTO.class)))
                .thenThrow(new RuntimeException("La calificación de amabilidad debe estar entre 1 y 5"));

        mockMvc.perform(post("/api/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void actualizarResena_ConCalificacionesInvalidas_DeberiaRetornarBadRequest() throws Exception {
        String jsonContent = """
            {
                "califAmabilidad": 7,
                "califClaridad": 8,
                "califExigencia": 9,
                "comentario": "Test con calificaciones inválidas",
                "esAnonimo": false
            }
            """;

        when(resenaService.actualizarResena(eq(1), any(ResenaDTO.class)))
                .thenThrow(new RuntimeException("La calificación de amabilidad debe estar entre 1 y 5"));

        mockMvc.perform(put("/api/resenas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void crearResena_ConCalificacionesValidas_DeberiaRetornarCreated() throws Exception {
        String jsonContent = """
            {
                "idUsuario": 1,
                "idProfesor": 1,
                "idCurso": 1,
                "califAmabilidad": 5,
                "califClaridad": 4,
                "califExigencia": 3,
                "comentario": "Test con calificaciones válidas",
                "esAnonimo": false,
                "utilidad": true
            }
            """;

        ResenaDTO resenaDTO = crearResenaDTO(1);
        when(resenaService.crearResena(any(ResenaDTO.class))).thenReturn(resenaDTO);

        mockMvc.perform(post("/api/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.califAmabilidad", is(5)))
                .andExpect(jsonPath("$.califClaridad", is(4)))
                .andExpect(jsonPath("$.califExigencia", is(3)));
    }
}
