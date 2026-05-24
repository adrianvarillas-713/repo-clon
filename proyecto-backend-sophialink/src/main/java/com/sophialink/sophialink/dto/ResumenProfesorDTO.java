package com.sophialink.sophialink.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResumenProfesorDTO {
    private Integer idProfesor;
    private String nombreProfesor;
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
    private String mejorAspecto;
    private String aspectoMejorar;

    // Constructores
    public ResumenProfesorDTO() {}

    public ResumenProfesorDTO(Integer idProfesor, String nombreProfesor, Integer totalResenas,
                             BigDecimal promedioAmabilidad, BigDecimal promedioClaridad, BigDecimal promedioExigencia,
                             BigDecimal promedioGeneral, Integer resenasUtiles, Integer resenasNoUtiles,
                             BigDecimal porcentajeUtilidad, Integer resenasAprobadas, Integer resenasPendientes,
                             Integer resenasRechazadas, LocalDateTime ultimaResena, String mejorAspecto, String aspectoMejorar) {
        this.idProfesor = idProfesor;
        this.nombreProfesor = nombreProfesor;
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
        this.mejorAspecto = mejorAspecto;
        this.aspectoMejorar = aspectoMejorar;
    }

    // Getters y Setters
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

    public String getMejorAspecto() {
        return mejorAspecto;
    }

    public void setMejorAspecto(String mejorAspecto) {
        this.mejorAspecto = mejorAspecto;
    }

    public String getAspectoMejorar() {
        return aspectoMejorar;
    }

    public void setAspectoMejorar(String aspectoMejorar) {
        this.aspectoMejorar = aspectoMejorar;
    }
}
