package com.sophialink.sophialink.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;
    
    @Column(name = "nombre_curso", nullable = false, length = 255)
    private String nombreCurso;
    
    @Column(name = "codigo_curso", length = 50)
    private String codigoCurso;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad", nullable = false)
    private Facultad facultad;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaCurso categoria;
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resena> resenas;
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Horario> horarios;
    
    // Enum para las categorías
    public enum CategoriaCurso {
        NEGOCIOS("Negocios"),
        INGENIERIA("Ingeniería"),
        TECNOLOGIA("Tecnología"),
        TRANSVERSAL("Transversal");
        
        private final String displayName;
        
        CategoriaCurso(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Constructores
    public Curso() {}
    
    public Curso(String nombreCurso, String codigoCurso, Facultad facultad, CategoriaCurso categoria) {
        this.nombreCurso = nombreCurso;
        this.codigoCurso = codigoCurso;
        this.facultad = facultad;
        this.categoria = categoria;
    }
    
    // Getters y Setters
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
    
    public String getCodigoCurso() {
        return codigoCurso;
    }
    
    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
    
    public Facultad getFacultad() {
        return facultad;
    }
    
    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
    
    public CategoriaCurso getCategoria() {
        return categoria;
    }
    
    public void setCategoria(CategoriaCurso categoria) {
        this.categoria = categoria;
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
