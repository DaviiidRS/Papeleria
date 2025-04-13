package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Empleado;
import com.example.Papeleria.Repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscar(Integer id) {
        return empleadoRepository.findById(Long.valueOf(id));
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizar(Empleado empleado) {
        if (empleado.getId_empleado() == null ||
                !empleadoRepository.existsById(Long.valueOf(empleado.getId_empleado()))) {
            throw new RuntimeException("Error al actualizar. Empleado no encontrado.");
        }
        return empleadoRepository.save(empleado);
    }

    public boolean eliminar(Integer id_empleado) {
        if (empleadoRepository.existsById(Long.valueOf(id_empleado))) {
            empleadoRepository.deleteById(Long.valueOf(id_empleado));
            return true;
        }
        return false;
    }
}

