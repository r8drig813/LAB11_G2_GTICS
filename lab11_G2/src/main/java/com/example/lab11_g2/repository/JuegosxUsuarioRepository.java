package com.example.lab11_g2.repository;

import com.example.lab11_g2.entity.Juegosxusuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface JuegosxUsuarioRepository extends JpaRepository<Juegosxusuario,Integer> {

}
