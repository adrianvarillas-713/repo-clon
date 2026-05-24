package com.sophialink.sophialink.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "universidades")
@NoArgsConstructor
public class Universidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_universidad")
    private Integer idUniversidad;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "dominio_correo", unique = true, length = 100)
    private String dominioCorreo;
    
    @OneToMany(mappedBy = "universidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Facultad> facultades;
    
    @OneToMany(mappedBy = "universidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}
