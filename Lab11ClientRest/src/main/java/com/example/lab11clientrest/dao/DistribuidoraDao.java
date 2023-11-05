package com.example.lab11clientrest.dao;

import com.example.lab11clientrest.entity.Distribuidoras;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DistribuidoraDao {

    public List<Distribuidoras> listar() {

        List<Distribuidoras> lista = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        String endPoint = "http://localhost:8080/distribuidora/list";

        ResponseEntity<Distribuidoras[]> responseEntity = restTemplate.getForEntity(endPoint, Distribuidoras[].class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            Distribuidoras[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }

        return lista;
    }

    public void guardar(Distribuidoras distribuidoras){

        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/distribuidora/registro";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Distribuidoras> httpEntity = new HttpEntity<>(distribuidoras,httpHeaders);

        if(distribuidoras.getId() ==null){
            restTemplate.postForEntity(endPoint,httpEntity,Distribuidoras.class);
        }else{
            restTemplate.put(endPoint,httpEntity,Distribuidoras.class);
        }

    }

}
