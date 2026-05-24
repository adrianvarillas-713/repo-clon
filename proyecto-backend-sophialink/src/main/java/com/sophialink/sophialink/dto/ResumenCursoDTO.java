package com.sophialink.sophialink.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResumenCursoDTO {
    private Integer idCurso;
    private String nombreCurso;
    private String codigoCurso;
    private Integer totalResenas;
    private BigDecimal promedioAmabilidad;
    private BigDecimal promedioClaridad;
    private BigDecimal promedioExigencia;
    private BigDecimal promedioGeneral;
    private Integer resenasUtiles;
    private Integer resenasNoUtiles;
    private BigDecimal porcentajeUtilidad;
    private Integer resenasAprobadas;
    private Integer resenasPendientes;
    private Integer resenasRechazadas;
    private LocalDateTime ultimaResena;
    private String profesorMejorEvaluado;
    private String profesorMenosEvaluado;
    private Integer totalProfesores;
    private String categoria;

    // Constructores
    public ResumenCursoDTO() {}

    public ResumenCursoDTO(Integer idCurso, String nombreCurso, String codigoCurso, Integer totalResenas,
                          BigDecimal promedioAmabilidad, BigDecimal promedioClaridad, BigDecimal promedioExigencia,
                          BigDecimal promedioGeneral, Integer resenasUtiles, Integer resenasNoUtiles,
                          BigDecimal porcentajeUtilidad, Integer resenasAprobadas, Integer resenasPendientes,
                          Integer resenasRechazadas, LocalDateTime ultimaResena, String profesorMejorEvaluado,
                          String profesorMenosEvaluado, Integer totalProfesores, String categoria) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.codigoCurso = codigoCurso;
        this.totalResenas = totalResenas;
        this.promedioAmabilidad = promedioAmabilidad;
        this.promedioClaridad = promedioClaridad;
        this.promedioExigencia = promedioExigencia;
        this.promedioGeneral = promedioGeneral;
        this.resenasUtiles = resenasUtiles;
        this.resenasNoUtiles = resenasNoUtiles;
        this.porcentajeUtilidad = porcentajeUtilidad;
        this.resenasAprobadas = resenasAprobadas;
        this.resenasPendientes = resenasPendientes;
        this.resenasRechazadas = resenasRechazadas;
        this.ultimaResena = ultimaResena;
        this.profesorMejorEvaluado = profesorMejorEvaluado;
        this.profesorMenosEvaluado = profesorMenosEvaluado;
        this.totalProfesores = totalProfesores;
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

    public Integer getTotalResenas() {
        return totalResenas;
    }

    public void setTotalResenas(Integer totalResenas) {
        this.totalResenas = totalResenas;
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

    public BigDecimal getPromedioGeneral() {
        return promedioGeneral;
    }

    public void setPromedioGeneral(BigDecimal promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }

    public Integer getResenasUtiles() {
        return resenasUtiles;
    }

    public void setResenasUtiles(Integer resenasUtiles) {
        this.resenasUtiles = resenasUtiles;
    }

    public Integer getResenasNoUtiles() {
        return resenasNoUtiles;
    }

    public void setResenasNoUtiles(Integer resenasNoUtiles) {
        this.resenasNoUtiles = resenasNoUtiles;
    }

    public BigDecimal getPorcentajeUtilidad() {
        return porcentajeUtilidad;
    }

    public void setPorcentajeUtilidad(BigDecimal porcentajeUtilidad) {
        this.porcentajeUtilidad = porcentajeUtilidad;
    }

    public Integer getResenasAprobadas() {
        return resenasAprobadas;
    }

    public void setResenasAprobadas(Integer resenasAprobadas) {
        this.resenasAprobadas = resenasAprobadas;
    }

    public Integer getResenasPendientes() {
        return resenasPendientes;
    }

    public void setResenasPendientes(Integer resenasPendientes) {
        this.resenasPendientes = resenasPendientes;
    }

    public Integer getResenasRechazadas() {
        return resenasRechazadas;
    }

    public void setResenasRechazadas(Integer resenasRechazadas) {
        this.resenasRechazadas = resenasRechazadas;
    }

    public LocalDateTime getUltimaResena() {
        return ultimaResena;
    }

    public void setUltimaResena(LocalDateTime ultimaResena) {
        this.ultimaResena = ultimaResena;
    }

    public String getProfesorMejorEvaluado() {
        return profesorMejorEvaluado;
    }

    public void setProfesorMejorEvaluado(String profesorMejorEvaluado) {
        this.profesorMejorEvaluado = profesorMejorEvaluado;
    }

    public String getProfesorMenosEvaluado() {
        return profesorMenosEvaluado;
    }

    public void setProfesorMenosEvaluado(String profesorMenosEvaluado) {
        this.profesorMenosEvaluado = profesorMenosEvaluado;
    }

    public Integer getTotalProfesores() {
        return totalProfesores;
    }

    public void setTotalProfesores(Integer totalProfesores) {
        this.totalProfesores = totalProfesores;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
