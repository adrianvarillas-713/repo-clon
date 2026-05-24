package com.sophialink.sophialink.service;

import com.sophialink.sophialink.dto.UsuarioDTO;
import com.sophialink.sophialink.entity.Usuario;
import com.sophialink.sophialink.entity.Universidad;
import com.sophialink.sophialink.repository.UsuarioRepository;
import com.sophialink.sophialink.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UniversidadRepository universidadRepository;
    
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public Optional<UsuarioDTO> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .map(this::convertirADTO);
    }
    
    public Optional<UsuarioDTO> obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreoInstitucional(correo)
                .map(this::convertirADTO);
    }
    
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Universidad universidad = universidadRepository.findById(usuarioDTO.getIdUniversidad())
        .orElseThrow(() -> new IllegalArgumentException(
            "Universidad no encontrada: " + usuarioDTO.getIdUniversidad()));
        
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
        usuario.setCorreoInstitucional(usuarioDTO.getCorreoInstitucional());
        usuario.setContrasena(usuarioDTO.getCorreoInstitucional()); // En producción, encriptar
        usuario.setUniversidad(universidad);
        usuario.setFechaRegistro(java.time.LocalDateTime.now());
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return convertirADTO(usuarioGuardado);
    }
    
    public Optional<UsuarioDTO> actualizarUsuario(Integer id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
                    usuario.setCorreoInstitucional(usuarioDTO.getCorreoInstitucional());
                    
                    if (usuarioDTO.getIdUniversidad() != null) {
                        Universidad universidad = universidadRepository.findById(usuarioDTO.getIdUniversidad())
                                .orElseThrow(() -> new RuntimeException("Universidad no encontrada"));
                        usuario.setUniversidad(universidad);
                    }
                    
                    Usuario usuarioActualizado = usuarioRepository.save(usuario);
                    return convertirADTO(usuarioActualizado);
                });
    }
    
    public boolean eliminarUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private UsuarioDTO convertirADTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombreCompleto(),
                usuario.getCorreoInstitucional(),
                usuario.getUniversidad().getIdUniversidad(),
                usuario.getUniversidad().getNombre(),
                usuario.getCorreoVerificado(),
                usuario.getFechaRegistro()
        );
    }
}
