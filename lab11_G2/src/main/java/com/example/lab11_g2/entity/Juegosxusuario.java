package com.example.lab11_g2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "juegosxusuario")
public class Juegosxusuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuegosxusuario", nullable = false)
    private Integer id;

    @ManyToOne( optional = false)
    @JoinColumn(name = "idjuego", nullable = false)
    private Juego idjuego;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario idusuario;

    @Column(name = "cantidad")
    private Integer cantidad;

}