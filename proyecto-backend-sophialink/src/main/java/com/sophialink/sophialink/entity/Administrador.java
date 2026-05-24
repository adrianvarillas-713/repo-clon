package com.sophialink.sophialink.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "administradores")
public class Administrador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Integer idAdmin;
    
    @Column(name = "nombre_completo", nullable = false, length = 255)
    private String nombreCompleto;
    
    @Column(name = "correo", unique = true, nullable = false, length = 255)
    private String correo;
    
    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolAdministrador rol;
    
    @OneToMany(mappedBy = "adminRevisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resena> resenasRevisadas;
    
    // Enum para los roles
    public enum RolAdministrador {
        ADMIN("Admin"),
        SUPER_ADMIN("SuperAdmin");
        
        private final String displayName;
        
        RolAdministrador(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Constructores
    public Administrador() {}
    
    public Administrador(String nombreCompleto, String correo, String contrasena, RolAdministrador rol) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    
    // Getters y Setters
    public Integer getIdAdmin() {
        return idAdmin;
    }
    
    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public RolAdministrador getRol() {
        return rol;
    }
    
    public void setRol(RolAdministrador rol) {
        this.rol = rol;
    }
    
    public List<Resena> getResenasRevisadas() {
        return resenasRevisadas;
    }
    
    public void setResenasRevisadas(List<Resena> resenasRevisadas) {
        this.resenasRevisadas = resenasRevisadas;
    }
}
