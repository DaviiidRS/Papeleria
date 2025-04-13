package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/buscar/{id_cliente}")
    public ResponseEntity<Object> buscar(@PathVariable Integer id_cliente) {
        Optional<Cliente> cliente = clienteService.buscar(id_cliente);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente con ID " + id_cliente + " no encontrado.");
        }
    }


    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Cliente cliente) {
        try {
            Cliente nuevo = clienteService.guardar(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el cliente: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Cliente cliente) {
        try {
            Cliente actualizado = clienteService.actualizar(cliente);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_cliente}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id_cliente) {
        boolean eliminado = clienteService.eliminar(id_cliente);
        if (eliminado) {
            return ResponseEntity.ok("Cliente eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado.");
        }
    }
}
