package com.example.lab11clientrest.controller;


import com.example.lab11clientrest.dao.JuegoDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/juego")
public class JuegosController {

    final JuegoDao juegoDao;

    public JuegosController(JuegoDao juegoDao) {
        this.juegoDao = juegoDao;
    }


    @GetMapping({"/list", "", "/"})
    public String listarProductos(Model model) {
        //model.addAttribute("listaProductos", productRepository.findAll());
        model.addAttribute("listaJuegos", juegoDao.listar());
        return "juegos/list";
    }

}
