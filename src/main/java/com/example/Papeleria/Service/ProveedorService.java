package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Proveedor;
import com.example.Papeleria.Repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> buscar(Integer id) {
        return proveedorRepository.findById(Long.valueOf(id));
    }

    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor actualizar(Proveedor proveedor) {
        if (proveedor.getId_proveedor() == null ||
                !proveedorRepository.existsById(Long.valueOf(proveedor.getId_proveedor()))) {
            throw new RuntimeException("Error al actualizar. Proveedor no encontrado.");
        }
        return proveedorRepository.save(proveedor);
    }

    public boolean eliminar(Integer id_proveedor) {
        if (proveedorRepository.existsById(Long.valueOf(id_proveedor))) {
            proveedorRepository.deleteById(Long.valueOf(id_proveedor));
            return true;
        }
        return false;
    }
}
