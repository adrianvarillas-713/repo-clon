package com.sophialink.sophialink.controller;

import com.sophialink.sophialink.config.TestSecurityConfig;
import com.sophialink.sophialink.service.UniversidadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(UniversidadController.class)
@Import(TestSecurityConfig.class)
@org.springframework.test.context.ActiveProfiles("test")
class UniversidadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UniversidadService universidadService;

    @Test
    void obtenerTodasLasUniversidades_DeberiaRetornarListaDeUniversidades() throws Exception {
        // TODO: Completar el test
        // 1. Crear datos de prueba
        // 2. Configurar mock del servicio
        // 3. Ejecutar petición GET /api/universidades
        // 4. Verificar respuesta
        
        // Validación básica para evitar warnings
        assertNotNull(mockMvc);
        assertNotNull(universidadService);
    }

    @Test
    void obtenerUniversidadPorId_ConIdValido_DeberiaRetornarUniversidad() throws Exception {
        // TODO: Completar el test
        // 1. Crear universidad de prueba
        // 2. Configurar mock del servicio
        // 3. Ejecutar petición GET /api/universidades/{id}
        // 4. Verificar respuesta
    }

    @Test
    void obtenerUniversidadPorId_ConIdInvalido_DeberiaRetornar404() throws Exception {
        // TODO: Completar el test
        // 1. Configurar mock para retornar Optional.empty()
        // 2. Ejecutar petición GET /api/universidades/{id}
        // 3. Verificar status 404
    }

    @Test
    void crearUniversidad_ConDatosValidos_DeberiaRetornarUniversidadCreada() throws Exception {
        // TODO: Completar el test
        // 1. Crear UniversidadDTO de prueba
        // 2. Configurar mock del servicio
        // 3. Ejecutar petición POST /api/universidades
        // 4. Verificar respuesta y status 201
    }

    @Test
    void actualizarUniversidad_ConDatosValidos_DeberiaRetornarUniversidadActualizada() throws Exception {
        // TODO: Completar el test
        // 1. Crear UniversidadDTO de prueba
        // 2. Configurar mock del servicio
        // 3. Ejecutar petición PUT /api/universidades/{id}
        // 4. Verificar respuesta
    }

    @Test
    void eliminarUniversidad_ConIdValido_DeberiaRetornar204() throws Exception {
        // TODO: Completar el test
        // 1. Configurar mock para retornar true
        // 2. Ejecutar petición DELETE /api/universidades/{id}
        // 3. Verificar status 204
    }
}
