package com.sophialink.sophialink.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EstadisticasGeneralesDTO {
    private Integer totalResenas;
    private Integer totalProfesores;
    private Integer totalCursos;
    private Integer totalUsuarios;
    private BigDecimal promedioGeneralAmabilidad;
    private BigDecimal promedioGeneralClaridad;
    private BigDecimal promedioGeneralExigencia;
    private BigDecimal promedioGeneralUtilidad;
    private Integer resenasUtiles;
    private Integer resenasNoUtiles;
    private BigDecimal porcentajeUtilidad;
    private Integer resenasAprobadas;
    private Integer resenasPendientes;
    private Integer resenasRechazadas;
    private LocalDateTime ultimaResena;
    private String profesorMejorEvaluado;
    private String cursoMejorEvaluado;
    private String categoriaMasPopular;
    private Integer resenasEsteMes;
    private Integer resenasEsteAno;

    // Constructores
    public EstadisticasGeneralesDTO() {}

    public EstadisticasGeneralesDTO(Integer totalResenas, Integer totalProfesores, Integer totalCursos,
                                   Integer totalUsuarios, BigDecimal promedioGeneralAmabilidad,
                                   BigDecimal promedioGeneralClaridad, BigDecimal promedioGeneralExigencia,
                                   BigDecimal promedioGeneralUtilidad, Integer resenasUtiles, Integer resenasNoUtiles,
                                   BigDecimal porcentajeUtilidad, Integer resenasAprobadas, Integer resenasPendientes,
                                   Integer resenasRechazadas, LocalDateTime ultimaResena, String profesorMejorEvaluado,
                                   String cursoMejorEvaluado, String categoriaMasPopular, Integer resenasEsteMes,
                                   Integer resenasEsteAno) {
        this.totalResenas = totalResenas;
        this.totalProfesores = totalProfesores;
        this.totalCursos = totalCursos;
        this.totalUsuarios = totalUsuarios;
        this.promedioGeneralAmabilidad = promedioGeneralAmabilidad;
        this.promedioGeneralClaridad = promedioGeneralClaridad;
        this.promedioGeneralExigencia = promedioGeneralExigencia;
        this.promedioGeneralUtilidad = promedioGeneralUtilidad;
        this.resenasUtiles = resenasUtiles;
        this.resenasNoUtiles = resenasNoUtiles;
        this.porcentajeUtilidad = porcentajeUtilidad;
        this.resenasAprobadas = resenasAprobadas;
        this.resenasPendientes = resenasPendientes;
        this.resenasRechazadas = resenasRechazadas;
        this.ultimaResena = ultimaResena;
        this.profesorMejorEvaluado = profesorMejorEvaluado;
        this.cursoMejorEvaluado = cursoMejorEvaluado;
        this.categoriaMasPopular = categoriaMasPopular;
        this.resenasEsteMes = resenasEsteMes;
        this.resenasEsteAno = resenasEsteAno;
    }

    // Getters y Setters
    public Integer getTotalResenas() {
        return totalResenas;
    }

    public void setTotalResenas(Integer totalResenas) {
        this.totalResenas = totalResenas;
    }

    public Integer getTotalProfesores() {
        return totalProfesores;
    }

    public void setTotalProfesores(Integer totalProfesores) {
        this.totalProfesores = totalProfesores;
    }

    public Integer getTotalCursos() {
        return totalCursos;
    }

    public void setTotalCursos(Integer totalCursos) {
        this.totalCursos = totalCursos;
    }

    public Integer getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(Integer totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public BigDecimal getPromedioGeneralAmabilidad() {
        return promedioGeneralAmabilidad;
    }

    public void setPromedioGeneralAmabilidad(BigDecimal promedioGeneralAmabilidad) {
        this.promedioGeneralAmabilidad = promedioGeneralAmabilidad;
    }

    public BigDecimal getPromedioGeneralClaridad() {
        return promedioGeneralClaridad;
    }

    public void setPromedioGeneralClaridad(BigDecimal promedioGeneralClaridad) {
        this.promedioGeneralClaridad = promedioGeneralClaridad;
    }

    public BigDecimal getPromedioGeneralExigencia() {
        return promedioGeneralExigencia;
    }

    public void setPromedioGeneralExigencia(BigDecimal promedioGeneralExigencia) {
        this.promedioGeneralExigencia = promedioGeneralExigencia;
    }

    public BigDecimal getPromedioGeneralUtilidad() {
        return promedioGeneralUtilidad;
    }

    public void setPromedioGeneralUtilidad(BigDecimal promedioGeneralUtilidad) {
        this.promedioGeneralUtilidad = promedioGeneralUtilidad;
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

    public String getCursoMejorEvaluado() {
        return cursoMejorEvaluado;
    }

    public void setCursoMejorEvaluado(String cursoMejorEvaluado) {
        this.cursoMejorEvaluado = cursoMejorEvaluado;
    }

    public String getCategoriaMasPopular() {
        return categoriaMasPopular;
    }

    public void setCategoriaMasPopular(String categoriaMasPopular) {
        this.categoriaMasPopular = categoriaMasPopular;
    }

    public Integer getResenasEsteMes() {
        return resenasEsteMes;
    }

    public void setResenasEsteMes(Integer resenasEsteMes) {
        this.resenasEsteMes = resenasEsteMes;
    }

    public Integer getResenasEsteAno() {
        return resenasEsteAno;
    }

    public void setResenasEsteAno(Integer resenasEsteAno) {
        this.resenasEsteAno = resenasEsteAno;
    }
}
