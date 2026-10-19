package com.estudiante.enviosdeafeteria.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PedidoRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String cliente;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @NotNull(message = "El total es obligatorio")
    @Min(value = 1, message = "El total debe ser mayor a cero")
    private Double total;

    // Lista de IDs de los productos que lleva el pedido
    private List<Long> productosIds;

    // Getters y Setters (Genera los tuyos con Alt+Insert en IntelliJ)
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public List<Long> getProductosIds() { return productosIds; }
    public void setProductosIds(List<Long> productosIds) { this.productosIds = productosIds; }
}