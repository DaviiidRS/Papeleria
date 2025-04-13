package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado", nativeQuery = true)
    List<Venta> ventasPorEmpleado(@Param("idEmpleado") Integer idEmpleado);

    @Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado AND id_cliente = :idCliente", nativeQuery = true)
    List<Venta> ventasPorEmpleadoYCliente(@Param("idEmpleado") Integer idEmpleado, @Param("idCliente") Integer idCliente);

}
