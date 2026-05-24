package com.sophialink.sophialink.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "facultades")
public class Facultad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facultad")
    private Integer idFacultad;
    
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_universidad", nullable = false)
    private Universidad universidad;
    
    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Profesor> profesores;
    
    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Curso> cursos;
    
    // Constructores
    public Facultad() {}
    
    public Facultad(String nombre, Universidad universidad) {
        this.nombre = nombre;
        this.universidad = universidad;
    }
    
    // Getters y Setters
    public Integer getIdFacultad() {
        return idFacultad;
    }
    
    public void setIdFacultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Universidad getUniversidad() {
        return universidad;
    }
    
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }
    
    public List<Profesor> getProfesores() {
        return profesores;
    }
    
    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }
    
    public List<Curso> getCursos() {
        return cursos;
    }
    
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
