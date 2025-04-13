package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Service.DetalleVentaService;
import com.example.Papeleria.Service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService ventaService;
    private final DetalleVentaService detalleVentaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Venta>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    @GetMapping("/buscar/{id_venta}")
    public ResponseEntity<?> buscar(@PathVariable Integer id_venta) {
        Optional<Venta> venta = ventaService.buscar(id_venta);
        if (venta.isPresent()){
            return ResponseEntity.ok(venta.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Venta con id " + id_venta + " no encontrada.");
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = ventaService.guardar(venta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la venta: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Venta venta) {
        try {
            Venta ventaActualizada = ventaService.actualizar(venta);
            return ResponseEntity.ok(ventaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar la venta: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_venta}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id_venta) {
        boolean eliminado = ventaService.eliminar(id_venta);
        if (eliminado) {
            return ResponseEntity.ok("Venta eliminada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada.");
        }
    }

    @GetMapping("/empleado/{id_empleado}")
    public ResponseEntity<List<Venta>> listarVentasPorEmpleado(@PathVariable Integer id_empleado) {
        List<Venta> ventas = ventaService.listarVentasPorEmpleado(id_empleado);
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/empleado/{idEmpleado}/cliente/{idCliente}")
    public ResponseEntity<List<Venta>> ventasPorEmpleadoYCliente(@PathVariable Integer idEmpleado, @PathVariable Integer idCliente) {
        List<Venta> ventas = ventaService.ventasPorEmpleadoYCliente(idEmpleado, idCliente);
        return ResponseEntity.ok(ventas);
    }


}
