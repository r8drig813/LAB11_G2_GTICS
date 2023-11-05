package com.example.lab11clientrest.dao;

import com.example.lab11clientrest.entity.Distribuidoras;
import com.example.lab11clientrest.entity.Editora;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EditoraDao {

    public List<Editora> listar() {

        List<Editora> lista = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        String endPoint = "http://localhost:8080/distribuidora/list";

        ResponseEntity<Editora[]> responseEntity = restTemplate.getForEntity(endPoint, Editora[].class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            Editora[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }

        return lista;
    }
}
