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
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_empleado;

    @Column(length = 10,nullable = false)
    private String nombre;

    @Column(length = 50,nullable = false)
    private String cargo;

    @Column(length = 50,nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "empleado")
    @JsonIgnore
    private List<Venta> ventas;


}
