package com.example.lab11clientrest.dao;

import com.example.lab11clientrest.entity.Distribuidoras;
import com.example.lab11clientrest.entity.Paises;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SedeDao {

    public List<Paises> listar() {

        List<Paises> lista = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        String endPoint = "http://localhost:8080/sede/list";

        ResponseEntity<Paises[]> responseEntity = restTemplate.getForEntity(endPoint, Paises[].class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            Paises[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }

        return lista;
    }
}
