package com.example.lab11clientrest.entity;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Editora {

    private Integer id;
    private String nombre;
    private String descripcion;

}