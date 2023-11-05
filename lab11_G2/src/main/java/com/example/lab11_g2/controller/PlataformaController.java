package com.example.lab11_g2.controller;

import com.example.lab11_g2.entity.Plataforma;
import com.example.lab11_g2.repository.PlataformasRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plataforma")
public class PlataformaController {

    final PlataformasRepository plataformasRepository;

    public PlataformaController(PlataformasRepository plataformasRepository) {
        this.plataformasRepository = plataformasRepository;
    }


    @GetMapping(value = {"/list", ""})
    public List<Plataforma> listar() {
        return plataformasRepository.findAll();
    }

}
