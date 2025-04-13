package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Service.DetalleVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalleVentas")
@RequiredArgsConstructor
public class DetalleVentaController {
    private final DetalleVentaService detalleVentaService;

    @GetMapping("/listar")
    public ResponseEntity<List<DetalleVenta>> listar() {
        return ResponseEntity.ok(detalleVentaService.listar());
    }

    @GetMapping("/buscar/{id_detalle}")
    public ResponseEntity<?> buscar(@PathVariable Integer id_detalle) {
        Optional<DetalleVenta> detalle = detalleVentaService.buscar(id_detalle);
        if(detalle.isPresent()){
            return ResponseEntity.ok(detalle.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Detalle de venta con id " + id_detalle + " no encontrado");
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalleVenta) {
        try {
            detalleVentaService.guardar(detalleVenta);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Detalle de venta creado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el detalle de venta: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody DetalleVenta detalleVenta) {
        try {
            DetalleVenta actualizado = detalleVentaService.actualizar(detalleVenta);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el detalle de venta: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_detalle}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id_detalle) {
        boolean eliminado = detalleVentaService.eliminar(id_detalle);
        if (eliminado) {
            return ResponseEntity.ok("Detalle de venta eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle de venta no encontrado.");
        }
    }

    @GetMapping("/empleado/{id_empleado}/cliente/{id_cliente}")
    public ResponseEntity<List<DetalleVenta>> obtenerDetallesPorEmpleadoYCliente(
            @PathVariable Integer id_empleado,
            @PathVariable Integer id_cliente) {
        List<DetalleVenta> detalles = detalleVentaService.DetallesPorEmpleadoYCliente(id_empleado, id_cliente);
        return ResponseEntity.ok(detalles);
    }

}