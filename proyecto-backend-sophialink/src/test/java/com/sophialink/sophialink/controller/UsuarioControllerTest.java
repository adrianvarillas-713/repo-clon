package com.sophialink.sophialink.controller;

import com.sophialink.sophialink.config.TestSecurityConfig;
import com.sophialink.sophialink.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(UsuarioController.class)
@Import(TestSecurityConfig.class)
@org.springframework.test.context.ActiveProfiles("test")
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void obtenerTodosLosUsuarios_DeberiaRetornarListaDeUsuarios() throws Exception {
        // TODO: Completar el test
        // 1. Crear lista de usuarios de prueba
        // 2. Configurar mock del servicio
        // 3. Ejecutar petición GET /api/usuarios
        // 4. Verificar respuesta
        
        // Validación básica para evitar warnings
        assertNotNull(mockMvc);
        assertNotNull(usuarioService);
    }

    @Test
    void obtenerUsuarioPorId_ConIdValido_DeberiaRetornarUsuario() throws Exception {
        // TODO: Completar el test
        // 1. Crear usuario de prueba
        // 2. Configurar mock del servicio
        // 3. Ejecutar petición GET /api/usuarios/{id}
        // 4. Verificar respuesta
    }

    @Test
    void obtenerUsuarioPorCorreo_ConCorreoValido_DeberiaRetornarUsuario() throws Exception {
        // TODO: Completar el test
        // 1. Crear usuario de prueba
        // 2. Configurar mock del servicio
        // 3. Ejecutar petición GET /api/usuarios/correo/{correo}
        // 4. Verificar respuesta
    }

    @Test
    void crearUsuario_ConDatosValidos_DeberiaRetornarUsuarioCreado() throws Exception {
        // TODO: Completar el test
        // 1. Crear UsuarioDTO de prueba
        // 2. Configurar mock del servicio
        // 3. Ejecutar petición POST /api/usuarios
        // 4. Verificar respuesta y status 201
    }

    @Test
    void actualizarUsuario_ConDatosValidos_DeberiaRetornarUsuarioActualizado() throws Exception {
        // TODO: Completar el test
        // 1. Crear UsuarioDTO de prueba
        // 2. Configurar mock del servicio
        // 3. Ejecutar petición PUT /api/usuarios/{id}
        // 4. Verificar respuesta
    }

    @Test
    void eliminarUsuario_ConIdValido_DeberiaRetornar204() throws Exception {
        // TODO: Completar el test
        // 1. Configurar mock para retornar true
        // 2. Ejecutar petición DELETE /api/usuarios/{id}
        // 3. Verificar status 204
    }
}
