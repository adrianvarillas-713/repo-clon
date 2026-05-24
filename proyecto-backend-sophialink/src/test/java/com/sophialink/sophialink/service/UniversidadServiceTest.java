package com.sophialink.sophialink.service;

import com.sophialink.sophialink.repository.UniversidadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UniversidadServiceTest {

    @Mock
    private UniversidadRepository universidadRepository;

    @InjectMocks
    private UniversidadService universidadService;

    @Test
    void obtenerTodasLasUniversidades_DeberiaRetornarListaDeUniversidades() {
        // TODO: Completar el test
        // 1. Crear lista de universidades de prueba
        // 2. Configurar mock del repositorio
        // 3. Ejecutar método del servicio
        // 4. Verificar resultado
        
        // Validación básica para evitar warnings
        assertNotNull(universidadRepository);
        assertNotNull(universidadService);
    }

    @Test
    void obtenerUniversidadPorId_ConIdValido_DeberiaRetornarUniversidad() {
        // TODO: Completar el test
        // 1. Crear universidad de prueba
        // 2. Configurar mock del repositorio
        // 3. Ejecutar método del servicio
        // 4. Verificar resultado
    }

    @Test
    void obtenerUniversidadPorId_ConIdInvalido_DeberiaRetornarOptionalVacio() {
        // TODO: Completar el test
        // 1. Configurar mock para retornar Optional.empty()
        // 2. Ejecutar método del servicio
        // 3. Verificar que retorna Optional.empty()
    }

    @Test
    void crearUniversidad_ConDatosValidos_DeberiaRetornarUniversidadCreada() {
        // TODO: Completar el test
        // 1. Crear UniversidadDTO de prueba
        // 2. Configurar mock del repositorio
        // 3. Ejecutar método del servicio
        // 4. Verificar resultado y que se llamó save()
    }

    @Test
    void actualizarUniversidad_ConIdValido_DeberiaRetornarUniversidadActualizada() {
        // TODO: Completar el test
        // 1. Crear universidad existente y DTO de actualización
        // 2. Configurar mocks del repositorio
        // 3. Ejecutar método del servicio
        // 4. Verificar resultado y que se llamó save()
    }

    @Test
    void actualizarUniversidad_ConIdInvalido_DeberiaRetornarOptionalVacio() {
        // TODO: Completar el test
        // 1. Configurar mock para retornar Optional.empty()
        // 2. Ejecutar método del servicio
        // 3. Verificar que retorna Optional.empty()
    }

    @Test
    void eliminarUniversidad_ConIdValido_DeberiaRetornarTrue() {
        // TODO: Completar el test
        // 1. Configurar mock para retornar true en existsById()
        // 2. Ejecutar método del servicio
        // 3. Verificar que retorna true y se llamó deleteById()
    }

    @Test
    void eliminarUniversidad_ConIdInvalido_DeberiaRetornarFalse() {
        // TODO: Completar el test
        // 1. Configurar mock para retornar false en existsById()
        // 2. Ejecutar método del servicio
        // 3. Verificar que retorna false y no se llamó deleteById()
    }
}
