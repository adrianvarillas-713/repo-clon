package com.sophialink.sophialink.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resenas")
public class Resena {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Integer idResena;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
    
    @Column(name = "calif_amabilidad", nullable = false)
    private Integer califAmabilidad;
    
    @Column(name = "calif_claridad", nullable = false)
    private Integer califClaridad;
    
    @Column(name = "calif_exigencia", nullable = false)
    private Integer califExigencia;
    
    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;
    
    @Column(name = "es_anonimo", nullable = false)
    private Boolean esAnonimo = false;
    
    @Column(name = "utilidad", nullable = false)
    private Boolean utilidad = false;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoResena estado = EstadoResena.PENDIENTE;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin_revisor")
    private Administrador adminRevisor;
    
    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;
    
    // Enum para los estados
    public enum EstadoResena {
        PENDIENTE("Pendiente"),
        APROBADA("Aprobada"),
        RECHAZADA("Rechazada");
        
        private final String displayName;
        
        EstadoResena(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Constructores
    public Resena() {}
    
    public Resena(Usuario usuario, Profesor profesor, Curso curso, 
                  Integer califAmabilidad, Integer califClaridad, Integer califExigencia, 
                  String comentario, Boolean utilidad) {
        this.usuario = usuario;
        this.profesor = profesor;
        this.curso = curso;
        this.califAmabilidad = califAmabilidad;
        this.califClaridad = califClaridad;
        this.califExigencia = califExigencia;
        this.comentario = comentario;
        this.utilidad = utilidad;
        this.fechaPublicacion = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Integer getIdResena() {
        return idResena;
    }
    
    public void setIdResena(Integer idResena) {
        this.idResena = idResena;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Profesor getProfesor() {
        return profesor;
    }
    
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
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
    
    public EstadoResena getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoResena estado) {
        this.estado = estado;
    }
    
    public Administrador getAdminRevisor() {
        return adminRevisor;
    }
    
    public void setAdminRevisor(Administrador adminRevisor) {
        this.adminRevisor = adminRevisor;
    }
    
    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }
    
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
