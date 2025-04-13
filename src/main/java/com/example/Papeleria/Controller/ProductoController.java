package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Producto;
import com.example.Papeleria.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/buscar/{id_producto}")
    public ResponseEntity<?> buscar(@PathVariable Integer id_producto) {
        Optional<Producto> producto = productoService.buscar(id_producto);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Producto con id " + id_producto + " no encontrado");
        }
    }


    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Producto producto) {
        try {
            productoService.guardar(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el producto: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Producto producto) {
        try {
            Producto actualizado = productoService.actualizar(producto);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_producto}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id_producto) {
        boolean eliminado = productoService.eliminar(id_producto);
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
        }
    }
    @GetMapping("/proveedor/{id_proveedor}")
    public ResponseEntity<List<Producto>> listarPorProveedor(@PathVariable Integer id_proveedor) {
        List<Producto> productos = productoService.listarPorProveedor(id_proveedor);
        return ResponseEntity.ok(productos);
    }


}