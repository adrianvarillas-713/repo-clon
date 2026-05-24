package com.sophialink.sophialink.service;

import com.sophialink.sophialink.dto.UniversidadDTO;
import com.sophialink.sophialink.entity.Universidad;
import com.sophialink.sophialink.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniversidadService {
    
    @Autowired
    private UniversidadRepository universidadRepository;
    
    public List<UniversidadDTO> obtenerTodasLasUniversidades() {
        List<Universidad> universidades = universidadRepository.findAll();
        return universidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public Optional<UniversidadDTO> obtenerUniversidadPorId(Integer id) {
        return universidadRepository.findById(id)
                .map(this::convertirADTO);
    }
    
    public UniversidadDTO crearUniversidad(UniversidadDTO universidadDTO) {
        Universidad universidad = new Universidad();
        universidad.setNombre(universidadDTO.getNombre());
        universidad.setDominioCorreo(universidadDTO.getDominioCorreo());
        
        Universidad universidadGuardada = universidadRepository.save(universidad);
        return convertirADTO(universidadGuardada);
    }
    
    public Optional<UniversidadDTO> actualizarUniversidad(Integer id, UniversidadDTO universidadDTO) {
        return universidadRepository.findById(id)
                .map(universidad -> {
                    universidad.setNombre(universidadDTO.getNombre());
                    universidad.setDominioCorreo(universidadDTO.getDominioCorreo());
                    Universidad universidadActualizada = universidadRepository.save(universidad);
                    return convertirADTO(universidadActualizada);
                });
    }
    
    public boolean eliminarUniversidad(Integer id) {
        if (universidadRepository.existsById(id)) {
            universidadRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private UniversidadDTO convertirADTO(Universidad universidad) {
        return new UniversidadDTO(
                universidad.getIdUniversidad(),
                universidad.getNombre(),
                universidad.getDominioCorreo()
        );
    }
}
