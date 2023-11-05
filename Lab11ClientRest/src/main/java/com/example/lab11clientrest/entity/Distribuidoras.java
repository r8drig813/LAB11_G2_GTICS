package com.example.lab11clientrest.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Distribuidoras {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String fundacion;
    private String web;
    private Paises idsede;


}
