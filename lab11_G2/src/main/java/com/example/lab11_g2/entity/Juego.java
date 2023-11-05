package com.example.lab11_g2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(value = {"image"})

@Entity
@Table(name = "juegos")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuego", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 448)
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "image", length = 400)
    private String image;

    @ManyToOne
    @JoinColumn(name = "idgenero")
    private Genero idgenero;

    @ManyToOne
    @JoinColumn(name = "idplataforma")
    private Plataforma idplataforma;

    @ManyToOne
    @JoinColumn(name = "ideditora")
    private Editora ideditora;

    @ManyToOne
    @JoinColumn(name = "iddistribuidora")
    private Distribuidora iddistribuidora;

}