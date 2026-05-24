package com.sophialink.sophialink.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "horarios")
public class Horario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Integer idHorario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false)
    private DiaSemana diaSemana;
    
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;
    
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;
    
    @Column(name = "ciclo_academico", nullable = false, length = 50)
    private String cicloAcademico;
    
    // Enum para los días de la semana
    public enum DiaSemana {
        LUNES("Lunes"),
        MARTES("Martes"),
        MIERCOLES("Miércoles"),
        JUEVES("Jueves"),
        VIERNES("Viernes"),
        SABADO("Sábado");
        
        private final String displayName;
        
        DiaSemana(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Constructores
    public Horario() {}
    
    public Horario(Profesor profesor, Curso curso, DiaSemana diaSemana, 
                   LocalTime horaInicio, LocalTime horaFin, String cicloAcademico) {
        this.profesor = profesor;
        this.curso = curso;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cicloAcademico = cicloAcademico;
    }
    
    // Getters y Setters
    public Integer getIdHorario() {
        return idHorario;
    }
    
    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
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
    
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }
    
    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    public LocalTime getHoraFin() {
        return horaFin;
    }
    
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    
    public String getCicloAcademico() {
        return cicloAcademico;
    }
    
    public void setCicloAcademico(String cicloAcademico) {
        this.cicloAcademico = cicloAcademico;
    }
}
