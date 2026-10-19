package com.estudiante.enviosdeafeteria.repository;

import com.estudiante.enviosdeafeteria.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}