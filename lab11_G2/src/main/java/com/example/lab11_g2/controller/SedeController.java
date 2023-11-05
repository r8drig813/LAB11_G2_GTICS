package com.example.lab11_g2.controller;

import com.example.lab11_g2.entity.Paises;
import com.example.lab11_g2.repository.PaisesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sede")
public class SedeController {
    final PaisesRepository paisesRepository;

    public SedeController(PaisesRepository paisesRepository) {
        this.paisesRepository = paisesRepository;
    }

    @GetMapping(value = {"/list", ""})
    public List<Paises> listar() {
        return paisesRepository.findAll();
    }
}
