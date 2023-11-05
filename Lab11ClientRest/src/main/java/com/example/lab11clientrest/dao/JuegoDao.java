package com.example.lab11clientrest.dao;


import com.example.lab11clientrest.entity.DistribuidoraDto;
import com.example.lab11clientrest.entity.Distribuidoras;
import com.example.lab11clientrest.entity.Juegos;
import com.example.lab11clientrest.entity.JuegosDto;
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

    public void guardar(Juegos juegos){

        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/juego/registro";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Juegos> httpEntity = new HttpEntity<>(juegos,httpHeaders);

        if(juegos.getId() ==null){
            restTemplate.postForEntity(endPoint,httpEntity,Juegos.class);
        }else{
            restTemplate.put(endPoint,httpEntity,Juegos.class);
        }
    }

    public Juegos buscarPorId(int id){

        Juegos juegos = null;

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/juego/" + id;

        ResponseEntity<JuegosDto> forEntity = restTemplate.getForEntity(url, JuegosDto.class);

        if(forEntity.getStatusCode().is2xxSuccessful()){
            JuegosDto juegosDto = forEntity.getBody();
            juegos = juegosDto.getJuego();
        }

        return juegos;
    }

    public void deleteProductById(int id){

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete("http://localhost:8080/juego/delete?id="+id);
    }

}
