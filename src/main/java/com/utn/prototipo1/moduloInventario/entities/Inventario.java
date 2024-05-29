package com.utn.prototipo1.moduloInventario.entities;

import com.utn.prototipo1.BaseEntidad.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inventario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Inventario extends BaseEntidad {

    @Column(name = "fecha_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesde;

    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;


    private int codInventario;



    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "codInventario")
    @Builder.Default
    private List<InventarioArticulo> inventarioArticulos = new ArrayList<>();

    public void agregarInventarioArticulo(InventarioArticulo inventarioArticulo){

        inventarioArticulos.add(inventarioArticulo);
    }
}