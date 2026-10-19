package com.estudiante.enviosdeafeteria.dto;

import java.time.LocalDateTime;

public class PedidoResponseDTO {
    private Long id;
    private String cliente;
    private String direccion;
    private Double total;
    private String estado;
    private LocalDateTime fecha;

    // Getters y Setters (Alt+Insert)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}