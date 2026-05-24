package com.sophialink.sophialink.dto;

public class UniversidadDTO {
    private Integer idUniversidad;
    private String nombre;
    private String dominioCorreo;
    
    // Constructores
    public UniversidadDTO() {}
    
    public UniversidadDTO(Integer idUniversidad, String nombre, String dominioCorreo) {
        this.idUniversidad = idUniversidad;
        this.nombre = nombre;
        this.dominioCorreo = dominioCorreo;
    }
    
    // Getters y Setters
    public Integer getIdUniversidad() {
        return idUniversidad;
    }
    
    public void setIdUniversidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDominioCorreo() {
        return dominioCorreo;
    }
    
    public void setDominioCorreo(String dominioCorreo) {
        this.dominioCorreo = dominioCorreo;
    }
}
