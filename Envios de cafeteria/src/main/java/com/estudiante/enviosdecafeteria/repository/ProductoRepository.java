package com.estudiante.enviosdecafeteria.repository;

import com.estudiante.enviosdecafeteria.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}