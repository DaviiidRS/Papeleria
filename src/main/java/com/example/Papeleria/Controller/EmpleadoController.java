package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Empleado;
import com.example.Papeleria.Service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Empleado>> listar() {
        return ResponseEntity.ok(empleadoService.listar());
    }

    @GetMapping("/buscar/{id_empleado}")
    public ResponseEntity<?> buscar(@PathVariable Integer id_empleado) {
        Optional<Empleado> empleado = empleadoService.buscar(id_empleado);
        if(empleado.isPresent()){
            return ResponseEntity.ok(empleado.get());
        } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Empleado con id " + id_empleado + " no encontrado");
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Empleado empleado) {
        try {
            empleadoService.guardar(empleado);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Empleado creado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el empleado: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Empleado empleado) {
        try {
            Empleado actualizado = empleadoService.actualizar(empleado);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_empleado}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id_empleado) {
        boolean eliminado = empleadoService.eliminar(id_empleado);
        if (eliminado) {
            return ResponseEntity.ok("Empleado eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado.");
        }
    }
}