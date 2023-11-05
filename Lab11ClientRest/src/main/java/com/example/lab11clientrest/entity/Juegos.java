package com.example.lab11clientrest.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Juegos {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private Genero idgenero;
    private Plataforma idplataforma;
    private Editora ideditora;
    private Distribuidoras iddistribuidora;
}
