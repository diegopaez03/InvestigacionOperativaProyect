package com.utn.prototipo1.moduloInventario.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inventario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Inventario extends BaseEntidad {

    @Column(name = "fecha_desde")
    private LocalDate fechaDesde;

    @Column(name = "fecha_hasta")
    private LocalDate fechaHasta;

    private int codInventario;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)  //Sirve que cuando eliminemos un inventario se borre todos los inventariosarticulos
    @JoinColumn(name = "CodArticulo")

    @Builder.Default
    private List<InventarioArticulo> inventarioArticulos = new ArrayList<>();
    public void agregarInventarioArticulo(InventarioArticulo inventarioArticulo){
        this.inventarioArticulos.add(inventarioArticulo);
        inventarioArticulo.setInventario(this);
    }
}
