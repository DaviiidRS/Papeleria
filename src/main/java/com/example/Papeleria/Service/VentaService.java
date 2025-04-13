package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Model.Empleado;
import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Repository.ClienteRepository;
import com.example.Papeleria.Repository.EmpleadoRepository;
import com.example.Papeleria.Repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaService {
    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;

    public List<Venta> listar() {
        List<Venta> ventas = ventaRepository.findAll();
        ventas.forEach(v -> {
            if (v.getCliente() != null) v.setId_cliente(v.getCliente().getId_cliente());
            if (v.getEmpleado() != null) v.setId_empleado(v.getEmpleado().getId_empleado());
        });
        return ventas;
    }

    public List<Venta> listarVentasPorEmpleado(Integer id_empleado) {
        List<Venta> ventas = ventaRepository.ventasPorEmpleado(id_empleado);
        for (Venta venta : ventas) {
            if (venta.getCliente() != null) {
                venta.setId_cliente(venta.getCliente().getId_cliente());
            }
            if (venta.getEmpleado() != null) {
                venta.setId_empleado(venta.getEmpleado().getId_empleado());
            }
        }
        return ventas;
    }


    public Optional<Venta> buscar(Integer id_venta) {
        Optional<Venta> venta = ventaRepository.findById(Long.valueOf(id_venta));
        venta.ifPresent(v -> {
            if (v.getCliente() != null) v.setId_cliente(v.getCliente().getId_cliente());
            if (v.getEmpleado() != null) v.setId_empleado(v.getEmpleado().getId_empleado());
        });
        return venta;
    }

    public Venta guardar(Venta venta) {
        if (venta.getId_cliente() != null) {
            Cliente cliente = clienteRepository.findById(Long.valueOf(venta.getId_cliente()))
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            venta.setCliente(cliente);
        }
        if (venta.getId_empleado() != null) {
            Empleado empleado = empleadoRepository.findById(Long.valueOf(venta.getId_empleado()))
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            venta.setEmpleado(empleado);
        }
        return ventaRepository.save(venta);
    }

    public Venta actualizar(Venta venta) {
        if (venta.getId_venta() == null || !ventaRepository.existsById(Long.valueOf(venta.getId_venta()))) {
            throw new RuntimeException("Venta no encontrada.");
        }
        if (venta.getId_cliente() != null) {
            Cliente cliente = clienteRepository.findById(Long.valueOf(venta.getId_cliente()))
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            venta.setCliente(cliente);
        }
        if (venta.getId_empleado() != null) {
            Empleado empleado = empleadoRepository.findById(Long.valueOf(venta.getId_empleado()))
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            venta.setEmpleado(empleado);
        }
        return ventaRepository.save(venta);
    }

    public boolean eliminar(Integer id) {
        if (ventaRepository.existsById(Long.valueOf(id))) {
            ventaRepository.deleteById(Long.valueOf(id));
            return true;
        }
        return false;
    }

    public List<Venta> obtenerVentasPorEmpleado(Integer idEmpleado) {
        return ventaRepository.ventasPorEmpleado(idEmpleado);
    }

    public List<Venta> ventasPorEmpleadoYCliente(Integer idEmpleado, Integer idCliente) {
        List<Venta> ventas = ventaRepository.ventasPorEmpleadoYCliente(idEmpleado, idCliente);
        for (Venta venta : ventas) {
            if (venta.getCliente() != null) {
                venta.setId_cliente(venta.getCliente().getId_cliente());
            }
            if (venta.getEmpleado() != null) {
                venta.setId_empleado(venta.getEmpleado().getId_empleado());
            }
        }
        return ventas;
    }



}
