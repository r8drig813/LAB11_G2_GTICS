package com.example.lab11_g2.repository;

import com.example.lab11_g2.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
    /*public User findByCorreo(String correo);*/

}
