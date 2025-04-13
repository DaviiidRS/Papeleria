package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    @Query(value = """
        SELECT dv.*
        FROM detalle_venta dv
        INNER JOIN venta v ON dv.id_venta = v.id_venta
        WHERE v.id_empleado = :id_empleado
          AND v.id_cliente = :id_cliente
    """, nativeQuery = true)
    List<DetalleVenta> detallesPorEmpleadoYCliente(@Param("id_empleado") Integer idEmpleado, @Param("id_cliente") Integer idCliente);
}
