package com.sophialink.sophialink.dto;

import java.time.LocalDateTime;

public class UsuarioDTO {
    private Integer idUsuario;
    private String nombreCompleto;
    private String correoInstitucional;
    private Integer idUniversidad;
    private String nombreUniversidad;
    private Boolean correoVerificado;
    private LocalDateTime fechaRegistro;
    
    // Constructores
    public UsuarioDTO() {}
    
    public UsuarioDTO(Integer idUsuario, String nombreCompleto, String correoInstitucional, 
                     Integer idUniversidad, String nombreUniversidad, Boolean correoVerificado, 
                     LocalDateTime fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoInstitucional = correoInstitucional;
        this.idUniversidad = idUniversidad;
        this.nombreUniversidad = nombreUniversidad;
        this.correoVerificado = correoVerificado;
        this.fechaRegistro = fechaRegistro;
    }
    
    // Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getCorreoInstitucional() {
        return correoInstitucional;
    }
    
    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }
    
    public Integer getIdUniversidad() {
        return idUniversidad;
    }
    
    public void setIdUniversidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }
    
    public String getNombreUniversidad() {
        return nombreUniversidad;
    }
    
    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }
    
    public Boolean getCorreoVerificado() {
        return correoVerificado;
    }
    
    public void setCorreoVerificado(Boolean correoVerificado) {
        this.correoVerificado = correoVerificado;
    }
    
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
