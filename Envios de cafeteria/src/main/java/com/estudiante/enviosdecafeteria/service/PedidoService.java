package com.estudiante.enviosdecafeteria.service;

import com.estudiante.enviosdecafeteria.dto.DetalleRequestDTO;
import com.estudiante.enviosdecafeteria.dto.PedidoRequestDTO;
import com.estudiante.enviosdecafeteria.dto.PedidoResponseDTO;
import com.estudiante.enviosdecafeteria.entity.DetallePedido;
import com.estudiante.enviosdecafeteria.entity.Pedido;
import com.estudiante.enviosdecafeteria.entity.Producto;
import com.estudiante.enviosdecafeteria.exception.RecursoNoEncontradoException;
import com.estudiante.enviosdecafeteria.exception.ReglaNegocioException;
import com.estudiante.enviosdecafeteria.repository.PedidoRepository;
import com.estudiante.enviosdecafeteria.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Pedido pedido = new Pedido();
        pedido.setCliente(dto.getCliente());
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("recibido");

        double totalCalculado = 0.0;
        List<DetallePedido> detalles = new ArrayList<>();

        for (DetalleRequestDTO det : dto.getDetalles()) {
            Producto producto = productoRepository.findById(det.getProductoId())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con ID: " + det.getProductoId()));

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(det.getCantidad());
            detalles.add(detalle);

            totalCalculado += producto.getPrecio() * det.getCantidad();
        }

        pedido.setDetalles(detalles);
        pedido.setTotal(totalCalculado);

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
                .orElseThrow(() -> new RecursoNoEncontradoException("Pedido no encontrado con ID: " + id));
        return convertirAResponseDTO(pedido);
    }

    public PedidoResponseDTO actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Pedido no encontrado con ID: " + id));

        // Validar los estados permitidos por el PF-21
        String estadoMin = nuevoEstado.toLowerCase();
        if (!estadoMin.equals("recibido") && !estadoMin.equals("en preparación")
                && !estadoMin.equals("listo") && !estadoMin.equals("entregado")) {
            throw new ReglaNegocioException("Estado no válido. Use: recibido, en preparación, listo o entregado");
        }

        pedido.setEstado(estadoMin);
        return convertirAResponseDTO(pedidoRepository.save(pedido));
    }

    private PedidoResponseDTO convertirAResponseDTO(Pedido pedido) {
        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setId(pedido.getId());
        response.setCliente(pedido.getCliente());
        response.setTotal(pedido.getTotal());
        response.setEstado(pedido.getEstado());
        response.setFecha(pedido.getFecha());
        return response;
    }
    public List<PedidoResponseDTO> filtrarPorEstado(String estado) {
        List<Pedido> pedidos = pedidoRepository.findByEstado(estado);
        List<PedidoResponseDTO> respuesta = new ArrayList<>();
        for (Pedido p : pedidos) {
            respuesta.add(convertirAResponseDTO(p));
        }
        return respuesta;
    }
    public Double obtenerVentasDelDia() {
        Double total = pedidoRepository.sumarVentasDelDia(LocalDate.now());
        return total != null ? total : 0.0;
    }
}