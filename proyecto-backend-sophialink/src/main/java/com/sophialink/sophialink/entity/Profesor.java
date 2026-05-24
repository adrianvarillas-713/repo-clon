package com.sophialink.sophialink.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "profesores")
public class Profesor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Integer idProfesor;
    
    @Column(name = "nombre_completo", nullable = false, length = 255)
    private String nombreCompleto;
    
    @Column(name = "correo_institucional", unique = true, length = 255)
    private String correoInstitucional;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad", nullable = false)
    private Facultad facultad;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "promedio_amabilidad", precision = 3, scale = 2)
    private BigDecimal promedioAmabilidad;
    
    @Column(name = "promedio_claridad", precision = 3, scale = 2)
    private BigDecimal promedioClaridad;
    
    @Column(name = "promedio_exigencia", precision = 3, scale = 2)
    private BigDecimal promedioExigencia;
    
    @Column(name = "correo_verificado", nullable = false)
    private Boolean correoVerificado = false;
    
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resena> resenas;
    
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Horario> horarios;
    
    // Constructores
    public Profesor() {}
    
    public Profesor(String nombreCompleto, Facultad facultad) {
        this.nombreCompleto = nombreCompleto;
        this.facultad = facultad;
    }
    
    // Getters y Setters
    public Integer getIdProfesor() {
        return idProfesor;
    }
    
    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
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
    
    public Facultad getFacultad() {
        return facultad;
    }
    
    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public BigDecimal getPromedioAmabilidad() {
        return promedioAmabilidad;
    }
    
    public void setPromedioAmabilidad(BigDecimal promedioAmabilidad) {
        this.promedioAmabilidad = promedioAmabilidad;
    }
    
    public BigDecimal getPromedioClaridad() {
        return promedioClaridad;
    }
    
    public void setPromedioClaridad(BigDecimal promedioClaridad) {
        this.promedioClaridad = promedioClaridad;
    }
    
    public BigDecimal getPromedioExigencia() {
        return promedioExigencia;
    }
    
    public void setPromedioExigencia(BigDecimal promedioExigencia) {
        this.promedioExigencia = promedioExigencia;
    }
    
    public Boolean getCorreoVerificado() {
        return correoVerificado;
    }
    
    public void setCorreoVerificado(Boolean correoVerificado) {
        this.correoVerificado = correoVerificado;
    }
    
    public List<Resena> getResenas() {
        return resenas;
    }
    
    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }
    
    public List<Horario> getHorarios() {
        return horarios;
    }
    
    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
