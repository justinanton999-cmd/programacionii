package com.estudiante.despensa.controller;

import com.estudiante.despensa.model.Producto;
import com.estudiante.despensa.model.ResumenInventario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new Producto(1L, "Leche Entera", "Lácteos", 6, 12.50));
        productos.add(new Producto(2L, "Queso Fresco", "Lácteos", 2, 28.00));
        productos.add(new Producto(3L, "Arroz", "Granos", 4, 8.50));
        productos.add(new Producto(4L, "Frijol Negro", "Granos", 3, 10.00));
        productos.add(new Producto(5L, "Jabón de Trastes", "Limpieza", 2, 15.75));
        productos.add(new Producto(6L, "Agua Mineral", "Bebidas", 8, 6.00));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(p);
            }
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Producto>> stockBajo() {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCantidad() <= 3) {
                resultado.add(p);
            }
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/mayor-valor")
    public ResponseEntity<Producto> mayorValor() {
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Producto mayor = productos.get(0);
        for (Producto p : productos) {
            if (p.calcularSubtotal() > mayor.calcularSubtotal()) {
                mayor = p;
            }
        }
        return ResponseEntity.ok(mayor);
    }

    @GetMapping("/resumen")
    public ResponseEntity<ResumenInventario> resumen() {
        int cantidadProductos = productos.size();
        int totalUnidades = 0;
        double valorTotal = 0.0;

        for (Producto p : productos) {
            totalUnidades += p.getCantidad();
            valorTotal += p.calcularSubtotal();
        }

        return ResponseEntity.ok(new ResumenInventario(cantidadProductos, totalUnidades, valorTotal));
    }
}