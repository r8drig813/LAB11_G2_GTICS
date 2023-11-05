package com.example.lab11clientrest.controller;


import com.example.lab11clientrest.dao.*;
import com.example.lab11clientrest.entity.Distribuidoras;
import com.example.lab11clientrest.entity.Juegos;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/juego")
public class JuegosController {

    final JuegoDao juegoDao;
    final PlataformaDao plataformaDao;
    final GeneroDao generoDao;
    final EditoraDao editoraDao;
    final DistribuidoraDao distribuidoraDao;
    public JuegosController(JuegoDao juegoDao, PlataformaDao plataformaDao, GeneroDao generoDao, EditoraDao editoraDao, DistribuidoraDao distribuidoraDao) {
        this.juegoDao = juegoDao;
        this.plataformaDao = plataformaDao;
        this.generoDao = generoDao;
        this.editoraDao = editoraDao;
        this.distribuidoraDao = distribuidoraDao;
    }


    @GetMapping({"/list", "", "/"})
    public String listarProductos(Model model) {
        //model.addAttribute("listaProductos", productRepository.findAll());
        model.addAttribute("listaJuegos", juegoDao.listar());
        return "juegos/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("juegos") Juegos juegos, Model model) {

        model.addAttribute("listaPlat", plataformaDao.listar());
        model.addAttribute("listaGenero", generoDao.listar());
        model.addAttribute("listaEditora", editoraDao.listar());
        model.addAttribute("listaDist", distribuidoraDao.listar());
        return "juegos/form";
    }

    @PostMapping("/save")
    public String guardarProducto(@ModelAttribute("juegos") @Valid Juegos juegos, BindingResult bindingResult,
                                  Model model, RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaPlat", plataformaDao.listar());
            model.addAttribute("listaGenero", generoDao.listar());
            model.addAttribute("listaEditora", editoraDao.listar());
            model.addAttribute("listaDist", distribuidoraDao.listar());
            return "juegos/form";
        } else {
            String msg = "Juego " + (juegos.getId() == null ? "creado" : "actualizado") + " exitosamente";
            attr.addFlashAttribute("msg", msg);
            // productRepository.save(product);
            juegoDao.guardar(juegos); //voy a hacer la validación de guardar o actualizar en el dao.
            return "redirect:/juego";
        }
    }

    @GetMapping("/edit")
    public String editarJuego(Model model, @RequestParam("id") int id) {

        Juegos juegosBuscar = juegoDao.buscarPorId(id);

        if (juegosBuscar != null) {
            model.addAttribute("juegos", juegosBuscar);
            model.addAttribute("listaPlat", plataformaDao.listar());
            model.addAttribute("listaGenero", generoDao.listar());
            model.addAttribute("listaEditora", editoraDao.listar());
            model.addAttribute("listaDist", distribuidoraDao.listar());
            return "juegos/form";
        } else {
            return "redirect:/juego";
        }
    }

}
