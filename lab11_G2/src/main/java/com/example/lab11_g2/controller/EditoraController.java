package com.example.lab11_g2.controller;

import com.example.lab11_g2.entity.Editora;
import com.example.lab11_g2.entity.Genero;
import com.example.lab11_g2.repository.EditoraRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/editora")
public class EditoraController {

    final EditoraRepository editoraRepository;

    public EditoraController(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }


    @GetMapping(value = {"/list", ""})
    public List<Editora> listar() {
        return editoraRepository.findAll();
    }

}
