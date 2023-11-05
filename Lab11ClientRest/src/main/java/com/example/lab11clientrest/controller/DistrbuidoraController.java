package com.example.lab11clientrest.controller;


import com.example.lab11clientrest.dao.DistribuidoraDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/distruibuidora")
public class DistrbuidoraController {

    final DistribuidoraDao distribuidoraDao;

    public DistrbuidoraController(DistribuidoraDao distribuidoraDao) {
        this.distribuidoraDao = distribuidoraDao;
    }

    @GetMapping({"/list", "", "/"})
    public String listarProductos(Model model) {
        //model.addAttribute("listaProductos", productRepository.findAll());
        model.addAttribute("listaDistribuidoras", distribuidoraDao.listar());
        return "distribuidoras/list";
    }

}
