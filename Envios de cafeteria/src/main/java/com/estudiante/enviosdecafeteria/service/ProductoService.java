package com.estudiante.enviosdecafeteria.service;

import com.estudiante.enviosdecafeteria.dto.ProductoRequestDTO;
import com.estudiante.enviosdecafeteria.entity.Producto;
import com.estudiante.enviosdecafeteria.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto crear(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        return productoRepository.save(producto);
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }
}