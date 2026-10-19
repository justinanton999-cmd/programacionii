package com.estudiante.enviosdeafeteria.repository;

import com.estudiante.enviosdeafeteria.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<com.estudiante.enviosdeafeteria.repository.Pedido> findByEstado(String estado);
}