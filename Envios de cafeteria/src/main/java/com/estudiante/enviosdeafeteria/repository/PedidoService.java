package com.estudiante.enviosdeafeteria.repository;

import com.estudiante.enviosdeafeteria.dto.PedidoRequestDTO;
import com.estudiante.enviosdeafeteria.dto.PedidoResponseDTO;
import com.estudiante.enviosdeafeteria.entity.Pedido;
import com.estudiante.enviosdeafeteria.entity.Producto;
import com.estudiante.enviosdeafeteria.repository.PedidoRepository;
import com.estudiante.enviosdeafeteria.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;
    public PedidoResponseDTO crearPedido(PedidoRequestDTO dto) {
        if (dto.getTotal() <= 0) {
            throw new IllegalArgumentException("El total debe ser mayor a cero");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCliente());
        pedido.setDireccion(dto.getDireccion());
        pedido.setTotal(dto.getTotal());
        pedido.setEstado("PENDIENTE");
        pedido.setFecha(LocalDateTime.now());

        if (dto.getProductosIds() != null && !dto.getProductosIds().isEmpty()) {
            List<Producto> productos = productoRepository.findAllById(dto.getProductosIds());
            pedido.setProductos(productos);
        }

        Pedido guardado = pedidoRepository.save(pedido);
        return convertirAResponseDTO(guardado);
    }

    public List<PedidoResponseDTO> listarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoResponseDTO> respuesta = new ArrayList<>();
        for (Pedido p : pedidos) {
            respuesta.add(convertirAResponseDTO(p));
        }
        return respuesta;
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
        return convertirAResponseDTO(pedido);
    }

    public PedidoResponseDTO actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));

        pedido.setEstado(nuevoEstado);
        Pedido actualizado = pedidoRepository.save(pedido);
        return convertirAResponseDTO(actualizado);
    }

    private PedidoResponseDTO convertirAResponseDTO(Pedido pedido) {
        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setId(pedido.getId());
        response.setCliente(pedido.getCliente());
        response.setDireccion(pedido.getDireccion());
        response.setTotal(pedido.getTotal());
        response.setEstado(pedido.getEstado());
        response.setFecha(pedido.getFecha());
        return response;
    }
}