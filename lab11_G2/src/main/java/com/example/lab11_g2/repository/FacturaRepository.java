package com.example.lab11_g2.repository;

import com.example.lab11_g2.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura,Integer> {
}
