package com.estudiante.enviosdecafeteria.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class PedidoRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String cliente;

    // Recibimos la lista de productos y cantidades
    @NotEmpty(message = "El pedido debe tener al menos un producto")
    @Valid
    private List<DetalleRequestDTO> detalles;

    // Getters y Setters
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public List<DetalleRequestDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleRequestDTO> detalles) { this.detalles = detalles; }
}