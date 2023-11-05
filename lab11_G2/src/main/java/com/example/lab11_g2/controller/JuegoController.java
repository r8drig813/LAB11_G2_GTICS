package com.example.lab11_g2.controller;

import com.example.lab11_g2.entity.Juego;
import com.example.lab11_g2.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/juego")
public class JuegoController {

    final DistribuidoraRepository distribuidoraRepository;
    final EditoraRepository editoraRepository;
    final FacturaRepository facturaRepository;
    final GenerosRepository generosRepository;
    final JuegoRepository juegoRepository;
    final JuegosxUsuarioRepository juegosxUsuarioRepository;
    final PaisesRepository paisesRepository;
    final PlataformasRepository plataformasRepository;
    final UserRepository userRepository;

    public JuegoController(DistribuidoraRepository distribuidoraRepository, EditoraRepository editoraRepository, FacturaRepository facturaRepository, GenerosRepository generosRepository, JuegoRepository juegoRepository, JuegosxUsuarioRepository juegosxUsuarioRepository, PaisesRepository paisesRepository, PlataformasRepository plataformasRepository, UserRepository userRepository) {
        this.distribuidoraRepository = distribuidoraRepository;
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
    public List<Juego> listaJuegos() {
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

    @PostMapping(value = {"/registro"})
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

    // ACTUALIZAR
    @PutMapping(value = {"/registro"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizar(Juego juegoRecibido) {

        HashMap<String, Object> rpta = new HashMap<>();

        if (juegoRecibido.getId() != null && juegoRecibido.getId() > 0) {

            Optional<Juego> byId = juegoRepository.findById(juegoRecibido.getId());
            if (byId.isPresent()) {
                Juego juegofromDb = byId.get();

                if (juegoRecibido.getNombre() != null)
                    juegofromDb.setNombre(juegoRecibido.getNombre());

                if (juegoRecibido.getDescripcion() != null)
                    juegofromDb.setDescripcion(juegoRecibido.getDescripcion());

                if (juegoRecibido.getPrecio() != null)
                    juegofromDb.setPrecio(juegoRecibido.getPrecio());

                if (juegoRecibido.getImage() != null)
                    juegofromDb.setImage(juegoRecibido.getImage());

                if (juegoRecibido.getIdgenero() != null)
                    juegofromDb.setIdgenero(juegoRecibido.getIdgenero());

                if (juegoRecibido.getIdplataforma() != null)
                    juegofromDb.setIdplataforma(juegoRecibido.getIdplataforma());

                if (juegoRecibido.getIdeditora() != null)
                    juegofromDb.setIdeditora(juegoRecibido.getIdeditora());

                if (juegoRecibido.getIddistribuidora() != null)
                    juegofromDb.setIddistribuidora(juegoRecibido.getIddistribuidora());


                juegoRepository.save(juegofromDb);
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El ID del juego enviado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("result", "error");
            rpta.put("msg", "debe enviar un juego con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }

    /*@PutMapping(value = "/actualizar")
    public ResponseEntity<HashMap<String,Object>> actualizarProducto(@RequestBody Juego juego) {

        HashMap<String, Object> responseMap = new HashMap<>();

        if (juego.getId() != null && juego.getId() > 0) {
            Optional<Juego> opt = juegoRepository.findById(juego.getId());
            if (opt.isPresent()) {
                juegoRepository.save(juego);
                responseMap.put("estado", "actualizado");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "El producto a actualizar no existe");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un ID");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }*/



    @DeleteMapping("/delete")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr){

        try{
            int id = Integer.parseInt(idStr);

            HashMap<String, Object> rpta = new HashMap<>();

            Optional<Juego> byId = juegoRepository.findById(id);
            if(byId.isPresent()){
                juegoRepository.deleteById(id);
                rpta.put("result","ok");
            }else{
                rpta.put("result","no ok");
                rpta.put("msg","el ID enviado no existe");
            }

            return ResponseEntity.ok(rpta);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un juego");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }

}
