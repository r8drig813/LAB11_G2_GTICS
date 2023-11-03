package com.example.lab11_g2.repository;

import com.example.lab11_g2.entity.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface JuegoRepository extends JpaRepository<Juego,Integer> {
    /*@Query(value = "Select  j.idjuego, j.nombre, j.descripcion, g.nombre as genero, j.image as imageURL from gameshop3.juegos j " +
            "inner join gameshop3.juegosxusuario ju  on j.idjuego=ju.idjuego " +
            "inner join gameshop3.usuarios u on ju.idusuario=u.idusuario " +
            "inner join gameshop3.generos g on g.idgenero=j.idgenero Where u.idusuario= ?",nativeQuery = true)
    List<JuegosUserDto> obtenerJuegosPorUser(int idusuario);

    @Transactional
    @Modifying
    @Query(value = "Insert INTO juegosxusuario (idusuario, idjuego, cantidad) VALUES (?,?,1)", nativeQuery = true)
    void registrarJuegoPorUser(int idusuario, int idjuego);*/
}
