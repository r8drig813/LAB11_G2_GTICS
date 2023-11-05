package com.example.lab11_g2.controller;

import com.example.lab11_g2.entity.Genero;
import com.example.lab11_g2.entity.Paises;
import com.example.lab11_g2.repository.GenerosRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    final GenerosRepository generosRepository;

    public GeneroController(GenerosRepository generosRepository) {
        this.generosRepository = generosRepository;
    }


    @GetMapping(value = {"/list", ""})
    public List<Genero> listar() {
        return generosRepository.findAll();
    }
}
