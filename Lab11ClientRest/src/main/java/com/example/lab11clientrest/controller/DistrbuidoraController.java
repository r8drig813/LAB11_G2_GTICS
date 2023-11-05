package com.example.lab11clientrest.controller;


import com.example.lab11clientrest.dao.DistribuidoraDao;
import com.example.lab11clientrest.dao.SedeDao;
import com.example.lab11clientrest.entity.Distribuidoras;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/distruibuidora")
public class DistrbuidoraController {

    final DistribuidoraDao distribuidoraDao;
    final SedeDao sedeDao;

    public DistrbuidoraController(DistribuidoraDao distribuidoraDao, SedeDao sedeDao) {
        this.distribuidoraDao = distribuidoraDao;
        this.sedeDao = sedeDao;
    }

    @GetMapping({"/list", "", "/"})
    public String listarDistribuidora(Model model) {
        //model.addAttribute("listaProductos", productRepository.findAll());
        model.addAttribute("listaDistribuidoras", distribuidoraDao.listar());
        return "distribuidoras/list";
    }

    @GetMapping("/new")
    public String nuevoDistribuidoraFrm(@ModelAttribute("distribuidoras") Distribuidoras distribuidoras, Model model) {

        model.addAttribute("listaPais", sedeDao.listar());
        return "distribuidoras/form";
    }

    @PostMapping("/save")
    public String guardarDistribuidora(@ModelAttribute("distribuidoras") @Valid Distribuidoras distribuidoras, BindingResult bindingResult,
                                  Model model, RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaPais", sedeDao.listar());
            return "distribuidoras/form";
        } else {
            String msg = "Distribuidora " + (distribuidoras.getId() == null ? "creado" : "actualizado") + " exitosamente";
            attr.addFlashAttribute("msg", msg);
            distribuidoraDao.guardar(distribuidoras); //voy a hacer la validación de guardar o actualizar en el dao.
            return "redirect:/distruibuidora";
        }
    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("distribuidoras") Distribuidoras distribuidoras,
                                      Model model, @RequestParam("id") int id) {

        Distribuidoras distribuidoras1 = distribuidoraDao.buscarPorId(id);


        if(distribuidoras1 != null) {

            distribuidoras = distribuidoras1;
            model.addAttribute("distribuidoras", distribuidoras);
            model.addAttribute("listaPais", sedeDao.listar());
            return "distribuidoras/form";
        } else {
            return "redirect:/distruibuidora";
        }
    }

}
