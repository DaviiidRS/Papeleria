package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Proveedor;
import com.example.Papeleria.Service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {
    private final ProveedorService proveedorService;

    @GetMapping("/listar")
    public ResponseEntity<List<Proveedor>> listar() {
        return ResponseEntity.ok(proveedorService.listar());
    }

    @GetMapping("/buscar/{id_proveedor}")
    public ResponseEntity<?> buscar(@PathVariable Integer id_proveedor) {
        Optional<Proveedor> proveedor = proveedorService.buscar(id_proveedor);
        if (proveedor.isPresent()){
            return ResponseEntity.ok(proveedor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor con id " + id_proveedor + " no encontrado");
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Proveedor proveedor) {
        try {
            proveedorService.guardar(proveedor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Proveedor creado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el proveedor: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Proveedor proveedor) {
        try {
            Proveedor actualizado = proveedorService.actualizar(proveedor);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el proveedor: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_proveedor}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id_proveedor) {
        boolean eliminado = proveedorService.eliminar(id_proveedor);
        if (eliminado) {
            return ResponseEntity.ok("Proveedor eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado.");
        }
    }
}