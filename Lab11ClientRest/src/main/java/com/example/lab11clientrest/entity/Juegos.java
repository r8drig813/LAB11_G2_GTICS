package com.example.lab11clientrest.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Juegos {

    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;

    @Digits(integer = 100000, fraction = 0)
    @Positive
    private Double precio;
    private String imagen;
    private Genero idgenero;
    private Plataforma idplataforma;
    private Editora ideditora;
    private Distribuidoras iddistribuidora;
}
