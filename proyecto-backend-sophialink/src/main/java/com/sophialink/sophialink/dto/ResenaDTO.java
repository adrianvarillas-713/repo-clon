package com.sophialink.sophialink.dto;

import java.time.LocalDateTime;

public class ResenaDTO {
    private Integer idResena;
    private Integer idUsuario;
    private String nombreUsuario;
    private Integer idProfesor;
    private String nombreProfesor;
    private Integer idCurso;
    private String nombreCurso;
    private Integer califAmabilidad;
    private Integer califClaridad;
    private Integer califExigencia;
    private String comentario;
    private Boolean esAnonimo;
    private Boolean utilidad;
    private String estado;
    private Integer idAdminRevisor;
    private String nombreAdminRevisor;
    private LocalDateTime fechaPublicacion;
    
    // Constructores
    public ResenaDTO() {}
    
    public ResenaDTO(Integer idResena, Integer idUsuario, String nombreUsuario, 
                    Integer idProfesor, String nombreProfesor, Integer idCurso, String nombreCurso,
                    Integer califAmabilidad, Integer califClaridad, Integer califExigencia, 
                    String comentario, Boolean esAnonimo, Boolean utilidad, String estado, 
                    Integer idAdminRevisor, String nombreAdminRevisor, LocalDateTime fechaPublicacion) {
        this.idResena = idResena;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idProfesor = idProfesor;
        this.nombreProfesor = nombreProfesor;
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.califAmabilidad = califAmabilidad;
        this.califClaridad = califClaridad;
        this.califExigencia = califExigencia;
        this.comentario = comentario;
        this.esAnonimo = esAnonimo;
        this.utilidad = utilidad;
        this.estado = estado;
        this.idAdminRevisor = idAdminRevisor;
        this.nombreAdminRevisor = nombreAdminRevisor;
        this.fechaPublicacion = fechaPublicacion;
    }
    
    // Getters y Setters
    public Integer getIdResena() {
        return idResena;
    }
    
    public void setIdResena(Integer idResena) {
        this.idResena = idResena;
    }
    
    public Integer getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public Integer getIdProfesor() {
        return idProfesor;
    }
    
    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }
    
    public String getNombreProfesor() {
        return nombreProfesor;
    }
    
    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }
    
    public Integer getIdCurso() {
        return idCurso;
    }
    
    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }
    
    public String getNombreCurso() {
        return nombreCurso;
    }
    
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    
    public Integer getCalifAmabilidad() {
        return califAmabilidad;
    }
    
    public void setCalifAmabilidad(Integer califAmabilidad) {
        this.califAmabilidad = califAmabilidad;
    }
    
    public Integer getCalifClaridad() {
        return califClaridad;
    }
    
    public void setCalifClaridad(Integer califClaridad) {
        this.califClaridad = califClaridad;
    }
    
    public Integer getCalifExigencia() {
        return califExigencia;
    }
    
    public void setCalifExigencia(Integer califExigencia) {
        this.califExigencia = califExigencia;
    }
    
    public String getComentario() {
        return comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public Boolean getEsAnonimo() {
        return esAnonimo;
    }
    
    public void setEsAnonimo(Boolean esAnonimo) {
        this.esAnonimo = esAnonimo;
    }
    
    public Boolean getUtilidad() {
        return utilidad;
    }
    
    public void setUtilidad(Boolean utilidad) {
        this.utilidad = utilidad;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Integer getIdAdminRevisor() {
        return idAdminRevisor;
    }
    
    public void setIdAdminRevisor(Integer idAdminRevisor) {
        this.idAdminRevisor = idAdminRevisor;
    }
    
    public String getNombreAdminRevisor() {
        return nombreAdminRevisor;
    }
    
    public void setNombreAdminRevisor(String nombreAdminRevisor) {
        this.nombreAdminRevisor = nombreAdminRevisor;
    }
    
    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }
    
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
