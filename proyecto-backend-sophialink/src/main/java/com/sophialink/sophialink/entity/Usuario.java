package com.sophialink.sophialink.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    @Column(name = "nombre_completo", nullable = false, length = 255)
    private String nombreCompleto;
    
    @Column(name = "correo_institucional", unique = true, nullable = false, length = 255)
    private String correoInstitucional;
    
    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_universidad", nullable = false)
    private Universidad universidad;
    
    @Column(name = "correo_verificado", nullable = false)
    private Boolean correoVerificado = false;
    
    @Column(name = "token_verificacion", length = 255)
    private String tokenVerificacion;
    
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resena> resenas;
    
    // Constructores
    public Usuario() {}
    
    public Usuario(String nombreCompleto, String correoInstitucional, String contrasena, Universidad universidad) {
        this.nombreCompleto = nombreCompleto;
        this.correoInstitucional = correoInstitucional;
        this.contrasena = contrasena;
        this.universidad = universidad;
        this.fechaRegistro = LocalDateTime.now();
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
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public Universidad getUniversidad() {
        return universidad;
    }
    
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }
    
    public Boolean getCorreoVerificado() {
        return correoVerificado;
    }
    
    public void setCorreoVerificado(Boolean correoVerificado) {
        this.correoVerificado = correoVerificado;
    }
    
    public String getTokenVerificacion() {
        return tokenVerificacion;
    }
    
    public void setTokenVerificacion(String tokenVerificacion) {
        this.tokenVerificacion = tokenVerificacion;
    }
    
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public List<Resena> getResenas() {
        return resenas;
    }
    
    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }
}
