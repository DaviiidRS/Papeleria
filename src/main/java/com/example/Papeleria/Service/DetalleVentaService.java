package com.example.Papeleria.Service;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Model.Producto;
import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Repository.DetalleVentaRepository;
import com.example.Papeleria.Repository.ProductoRepository;
import com.example.Papeleria.Repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleVentaService {
    private final DetalleVentaRepository detalleVentaRepository;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public List<DetalleVenta> listar() {
        List<DetalleVenta> detalleVentas = detalleVentaRepository.findAll();
        detalleVentas.forEach(d -> {
            if (d.getVenta() != null) {
                d.setId_venta(d.getVenta().getId_venta());
            }
            if (d.getProducto() != null) {
                d.setId_producto(d.getProducto().getId_producto());
            }
        });
        return detalleVentas;
    }

    public Optional<DetalleVenta> buscar(Integer id_detalle) {
        Optional<DetalleVenta> detalleVenta = detalleVentaRepository.findById(Long.valueOf(id_detalle));
        detalleVenta.ifPresent(d -> {
            if (d.getVenta() != null) {
                d.setId_venta(d.getVenta().getId_venta());
            }
            if (d.getProducto() != null) {
                d.setId_producto(d.getProducto().getId_producto());
            }
        });
        return detalleVenta;
    }

    public DetalleVenta guardar(DetalleVenta detalle) {
        if (detalle.getId_venta() != null) {
            Venta venta = ventaRepository.findById(Long.valueOf(detalle.getId_venta()))
                    .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
            detalle.setVenta(venta);
        }
        if (detalle.getId_producto() != null) {
            Producto producto = productoRepository.findById(Long.valueOf(detalle.getId_producto()))
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            detalle.setProducto(producto);
        }
        DetalleVenta guardado = detalleVentaRepository.save(detalle);
        // Setear nuevamente los IDs para que aparezcan en el response
        guardado.setId_venta(guardado.getVenta().getId_venta());
        guardado.setId_producto(guardado.getProducto().getId_producto());
        return guardado;
    }

    public DetalleVenta actualizar(DetalleVenta detalle) {
        if (detalle.getId_detalle() == null ||
                !detalleVentaRepository.existsById(Long.valueOf(detalle.getId_detalle()))) {
            throw new RuntimeException("DetalleVenta no encontrado.");
        }
        if (detalle.getId_venta() != null) {
            Venta venta = ventaRepository.findById(Long.valueOf(detalle.getId_venta()))
                    .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
            detalle.setVenta(venta);
        }
        if (detalle.getId_producto() != null) {
            Producto producto = productoRepository.findById(Long.valueOf(detalle.getId_producto()))
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            detalle.setProducto(producto);
        }
        DetalleVenta actualizado = detalleVentaRepository.save(detalle);
        // Volver a setear IDs para respuesta limpia
        actualizado.setId_venta(actualizado.getVenta().getId_venta());
        actualizado.setId_producto(actualizado.getProducto().getId_producto());
        return actualizado;
    }


    public boolean eliminar(Integer id_detalle) {
        if (detalleVentaRepository.existsById(Long.valueOf(id_detalle))) {
            detalleVentaRepository.deleteById(Long.valueOf(id_detalle));
            return true;
        }
        return false;
    }

    public List<DetalleVenta> DetallesPorEmpleadoYCliente(Integer idEmpleado, Integer idCliente) {
        List<DetalleVenta> detalleVentas = detalleVentaRepository.detallesPorEmpleadoYCliente(idEmpleado, idCliente);
        for (DetalleVenta detalleVenta: detalleVentas) {
            if (detalleVenta.getVenta() != null) {
                detalleVenta.setId_venta(detalleVenta.getVenta().getId_venta());
            }
            if (detalleVenta.getProducto() != null) {
                detalleVenta.setId_producto(detalleVenta.getProducto().getId_producto());
            }
        }
        return detalleVentas;
    }

}
