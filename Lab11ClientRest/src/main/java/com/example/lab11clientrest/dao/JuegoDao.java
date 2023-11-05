package com.example.lab11clientrest.dao;


import com.example.lab11clientrest.entity.Distribuidoras;
import com.example.lab11clientrest.entity.Juegos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JuegoDao {

    public List<Juegos> listar() {

        List<Juegos> lista = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        String endPoint = "http://localhost:8080/juego/list";

        ResponseEntity<Juegos[]> responseEntity = restTemplate.getForEntity(endPoint, Juegos[].class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            Juegos[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }

        return lista;
    }

}
