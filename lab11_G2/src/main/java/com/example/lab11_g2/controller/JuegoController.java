package com.example.lab11_g2.controller;

import com.example.lab11_g2.entity.Juego;
import com.example.lab11_g2.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/juego")
public class JuegoController {

    final DistribuidorasRepository distribuidorasRepository;
    final EditoraRepository editoraRepository;
    final FacturaRepository facturaRepository;
    final GenerosRepository generosRepository;
    final JuegoRepository juegoRepository;
    final JuegosxUsuarioRepository juegosxUsuarioRepository;
    final PaisesRepository paisesRepository;
    final PlataformasRepository plataformasRepository;
    final UserRepository userRepository;

    public JuegoController(DistribuidorasRepository distribuidorasRepository, EditoraRepository editoraRepository, FacturaRepository facturaRepository, GenerosRepository generosRepository, JuegoRepository juegoRepository, JuegosxUsuarioRepository juegosxUsuarioRepository, PaisesRepository paisesRepository, PlataformasRepository plataformasRepository, UserRepository userRepository) {
        this.distribuidorasRepository = distribuidorasRepository;
        this.editoraRepository = editoraRepository;
        this.facturaRepository = facturaRepository;
        this.generosRepository = generosRepository;
        this.juegoRepository = juegoRepository;
        this.juegosxUsuarioRepository = juegosxUsuarioRepository;
        this.paisesRepository = paisesRepository;
        this.plataformasRepository = plataformasRepository;
        this.userRepository = userRepository;
    }

    //LISTAR
    @GetMapping(value = {"/list", ""})
    public List<Juego> listaProductos() {
        return juegoRepository.findAll();
    }

    //OBTENER
    @GetMapping(value = "/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarJuego(@PathVariable("id") String idStr) {


        try {
            int id = Integer.parseInt(idStr);
            Optional<Juego> byId = juegoRepository.findById(id);

            HashMap<String, Object> respuesta = new HashMap<>();

            if (byId.isPresent()) {
                respuesta.put("result", "ok");
                respuesta.put("juego", byId.get());
            } else {
                respuesta.put("result", "no existe");
            }
            return ResponseEntity.ok(respuesta);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(value = {"", "/registro"})
    public ResponseEntity<HashMap<String, Object>> guardarJuego(
            @RequestBody Juego juego,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        juegoRepository.save(juego);
        if (fetchId) {
            responseJson.put("id", juego.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

}
