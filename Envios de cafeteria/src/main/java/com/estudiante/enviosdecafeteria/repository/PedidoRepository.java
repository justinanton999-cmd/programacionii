package com.estudiante.enviosdecafeteria.repository;

import com.estudiante.enviosdecafeteria.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEstado(String estado);
    @Query("SELECT SUM(p.total) FROM Pedido p WHERE CAST(p.fecha AS date) = :fecha")
    Double sumarVentasDelDia(@Param("fecha") LocalDate fecha);
}