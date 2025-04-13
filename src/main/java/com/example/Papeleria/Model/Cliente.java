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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;

    @Column(length = 50,nullable = false)
    private String nombre;

    @Column(length = 50,nullable = false)
    private String cedula;

    @Column(length = 50,nullable = false)
    private String telefono;

    @Column(length = 100,nullable = false)
    private String correo;

    @JsonIgnore
    @Transient
    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;
}
