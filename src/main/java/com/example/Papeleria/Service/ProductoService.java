package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Producto;
import com.example.Papeleria.Model.Proveedor;
import com.example.Papeleria.Repository.ProductoRepository;
import com.example.Papeleria.Repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;

    public List<Producto> listar() {
        List<Producto> productos = productoRepository.findAll();
        productos.forEach(p -> {
            if (p.getProveedor() != null) {
                p.setId_proveedor(p.getProveedor().getId_proveedor());
            }
        });
        return productos;
    }


    public Optional<Producto> buscar(Integer id_producto) {
        Optional<Producto> producto = productoRepository.findById(Long.valueOf(id_producto));
        producto.ifPresent(p -> {
            if (p.getProveedor() != null) {
                p.setId_proveedor(p.getProveedor().getId_proveedor());
            }
        });
        return producto;
    }


    public Producto guardar(Producto producto) {
        try {
            if (producto.getId_proveedor() != null) {
                Proveedor proveedor = proveedorRepository.findById(Long.valueOf(producto.getId_proveedor()))
                        .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
                producto.setProveedor(proveedor);
            }
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el producto: " + e.getMessage());
        }
    }


    public Producto actualizar(Producto producto) {
        if (producto.getId_producto() == null ||
                !productoRepository.existsById(Long.valueOf(producto.getId_producto()))) {
            throw new RuntimeException("Error al actualizar. Producto no encontrado.");
        }
        if (producto.getId_proveedor() != null) {
            Proveedor proveedor = proveedorRepository.findById(Long.valueOf(producto.getId_proveedor()))
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
            producto.setProveedor(proveedor);
        }
        return productoRepository.save(producto);
    }


    public boolean eliminar(Integer id) {
        if (productoRepository.existsById(Long.valueOf(id))) {
            productoRepository.deleteById(Long.valueOf(id));
            return true;
        }
        return false;
    }

    public List<Producto> listarPorProveedor(Integer id_proveedor) {
        List<Producto> productos = productoRepository.productosPorProveedor(id_proveedor);
        for (Producto producto : productos) {
            if (producto.getProveedor() != null) {
                producto.setId_proveedor(producto.getProveedor().getId_proveedor());
            }
        }
        return productos;
    }


}
