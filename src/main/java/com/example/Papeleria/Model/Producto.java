package com.example.Papeleria.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    @Column(length = 100,nullable = false)
    private String nombre;

    private String descripcion;
    private Double precio;
    private int stock;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_proveedor")
    private Proveedor proveedor;

    @Transient
    private Integer id_proveedor;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<DetalleVenta> detalle_ventas;

}
