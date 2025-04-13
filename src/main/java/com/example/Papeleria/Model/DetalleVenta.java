package com.example.Papeleria.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle;

    @ManyToOne
    @JoinColumn(name="id_venta")
    @JsonIgnore
    private Venta venta;

    @ManyToOne
    @JoinColumn(name="id_producto")
    @JsonIgnore
    private Producto producto;

    @Transient
    private Integer id_venta;

    @Transient
    private Integer id_producto;

    private int cantidad;
    private double precio_unitario;
}
