package com.example.lab11clientrest.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Distribuidoras {

    private Integer id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @Digits(integer = 10000, fraction = 0)
    @Positive
    private int fundacion;

    @NotBlank
    private String web;

    private Paises idsede;


}
