package com.example.lab11_g2.repository;


import com.example.lab11_g2.entity.Distribuidora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DistribuidoraRepository extends JpaRepository<Distribuidora,Integer> {
}
