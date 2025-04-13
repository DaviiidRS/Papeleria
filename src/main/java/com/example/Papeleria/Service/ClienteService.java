package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscar(Integer id) {
        return clienteRepository.findById(Long.valueOf(id));
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(Cliente cliente) {
        if (cliente.getId_cliente() == null || !clienteRepository.existsById(Long.valueOf(cliente.getId_cliente()))) {
            throw new RuntimeException("Cliente no encontrado.");
        }
        return clienteRepository.save(cliente);
    }

    public boolean eliminar(Integer id) {
        if (clienteRepository.existsById(Long.valueOf(id))) {
            clienteRepository.deleteById(Long.valueOf(id));
            return true;
        }
        return false;
    }
}
