package com.example.lab11clientrest.dao;

import com.example.lab11clientrest.entity.Distribuidoras;
import com.example.lab11clientrest.entity.Genero;
import com.example.lab11clientrest.entity.Plataforma;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GeneroDao {

    public List<Genero> listar() {

        List<Genero> lista = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        String endPoint = "http://localhost:8080/genero/list";

        ResponseEntity<Genero[]> responseEntity = restTemplate.getForEntity(endPoint, Genero[].class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            Genero[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }

        return lista;
    }
}
