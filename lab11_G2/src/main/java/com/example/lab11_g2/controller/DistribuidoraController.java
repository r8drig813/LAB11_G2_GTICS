package com.example.lab11_g2.controller;

import com.example.lab11_g2.entity.Distribuidora;
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
@RequestMapping("/distribuidora")
public class DistribuidoraController {

    final DistribuidoraRepository distribuidoraRepository;
    final EditoraRepository editoraRepository;
    final FacturaRepository facturaRepository;
    final GenerosRepository generosRepository;
    final JuegoRepository juegoRepository;
    final JuegosxUsuarioRepository juegosxUsuarioRepository;
    final PaisesRepository paisesRepository;
    final PlataformasRepository plataformasRepository;
    final UserRepository userRepository;

    public DistribuidoraController(DistribuidoraRepository distribuidoraRepository, EditoraRepository editoraRepository, FacturaRepository facturaRepository, GenerosRepository generosRepository, JuegoRepository juegoRepository, JuegosxUsuarioRepository juegosxUsuarioRepository, PaisesRepository paisesRepository, PlataformasRepository plataformasRepository, UserRepository userRepository) {
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
    public List<Distribuidora> listaDistribuidoras() {
        return distribuidoraRepository.findAll();
    }

    //OBTENER
    @GetMapping(value = "/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarDistribuidora(@PathVariable("id") String idStr) {


        try {
            int id = Integer.parseInt(idStr);
            Optional<Distribuidora> byId = distribuidoraRepository.findById(id);

            HashMap<String, Object> respuesta = new HashMap<>();

            if (byId.isPresent()) {
                respuesta.put("result", "ok");
                respuesta.put("distribuidora", byId.get());
            } else {
                respuesta.put("result", "no existe");
            }
            return ResponseEntity.ok(respuesta);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(value = {"/registro"})
    public ResponseEntity<HashMap<String, Object>> guardarDistribuidora(
            @RequestBody Distribuidora distribuidora,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        distribuidoraRepository.save(distribuidora);
        if (fetchId) {
            responseJson.put("id", distribuidora.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    // ACTUALIZAR
    @PutMapping(value = { "/registro"})
    public ResponseEntity<HashMap<String, Object>> actualizar(@RequestBody Distribuidora distribuidoraRecibido) {

        HashMap<String, Object> rpta = new HashMap<>();

        if (distribuidoraRecibido.getId() != null && distribuidoraRecibido.getId() > 0) {

            Optional<Distribuidora> byId = distribuidoraRepository.findById(distribuidoraRecibido.getId());
            if (byId.isPresent()) {
                Distribuidora distribuidorafromDb = byId.get();

                if (distribuidoraRecibido.getNombre() != null)
                    distribuidorafromDb.setNombre(distribuidoraRecibido.getNombre());

                if (distribuidoraRecibido.getDescripcion() != null)
                    distribuidorafromDb.setDescripcion(distribuidoraRecibido.getDescripcion());

                if (distribuidoraRecibido.getFundacion() != null)
                    distribuidorafromDb.setFundacion(distribuidoraRecibido.getFundacion());

                if (distribuidoraRecibido.getWeb() != null)
                    distribuidorafromDb.setWeb(distribuidoraRecibido.getWeb());

                if (distribuidoraRecibido.getIdsede() != null)
                    distribuidorafromDb.setIdsede(distribuidoraRecibido.getIdsede());

                distribuidoraRepository.save(distribuidorafromDb);
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El ID del distribuidora enviado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("result", "error");
            rpta.put("msg", "debe enviar un distribuidora con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr){

        try{
            int id = Integer.parseInt(idStr);

            HashMap<String, Object> rpta = new HashMap<>();

            Optional<Distribuidora> byId = distribuidoraRepository.findById(id);
            if(byId.isPresent()){
                distribuidoraRepository.deleteById(id);
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
            responseMap.put("msg", "Debe enviar una distribuidora");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
