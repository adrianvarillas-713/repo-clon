package com.sophialink.sophialink.controller;

import com.sophialink.sophialink.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(TestController.class)
@Import(TestSecurityConfig.class)
@org.springframework.test.context.ActiveProfiles("test")
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthCheck_DeberiaRetornarStatusOK() throws Exception {
        // TODO: Completar el test
        // 1. Ejecutar petición GET /api/test/health
        // 2. Verificar status 200
        // 3. Verificar que el JSON contiene "status": "OK"
        // 4. Verificar que el JSON contiene "message"
        // 5. Verificar que el JSON contiene "timestamp"
        
        // Validación básica para evitar warnings
        assertNotNull(mockMvc);
    }

    @Test
    void getInfo_DeberiaRetornarInformacionDeLaAPI() throws Exception {
        // TODO: Completar el test
        // 1. Ejecutar petición GET /api/test/info
        // 2. Verificar status 200
        // 3. Verificar que el JSON contiene "application": "SophiaLink"
        // 4. Verificar que el JSON contiene "version"
        // 5. Verificar que el JSON contiene "description"
        // 6. Verificar que el JSON contiene "endpoints"
        
        // Validación básica para evitar warnings
        assertNotNull(mockMvc);
    }
}
