package com.estudiante.enviosdeafeteria.controller;

import com.estudiante.enviosdeafeteria.dto.PedidoRequestDTO;
import com.estudiante.enviosdeafeteria.dto.PedidoResponseDTO;
import com.estudiante.enviosdeafeteria.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crear(@Valid @RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO nuevoPedido = pedidoService.crearPedido(dto);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED); // Devuelve 201
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listar() {
        return ResponseEntity.ok(pedidoService.listarPedidos()); // Devuelve 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<PedidoResponseDTO> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) { // Se pasa como ?nuevoEstado=ENVIADO
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, nuevoEstado));
    }
}