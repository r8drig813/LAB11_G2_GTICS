package com.example.lab11clientrest.dao;

import com.example.lab11clientrest.entity.Distribuidoras;
import com.example.lab11clientrest.entity.Plataforma;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PlataformaDao {

    public List<Plataforma> listar() {

        List<Plataforma> lista = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        String endPoint = "http://localhost:8080/plataforma/list";

        ResponseEntity<Plataforma[]> responseEntity = restTemplate.getForEntity(endPoint, Plataforma[].class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            Plataforma[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }

        return lista;
    }
}
