package com.utn.prototipo1.moduloArticulo.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "articulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Articulo extends BaseEntidad {


    private String nombreArticulo;
    private int precio;


    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;


    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "codCategoria")
    private ArticuloCategoria articuloCategoria;

    @ManyToOne()
    @JoinColumn(name = "codArticuloInventario")
    private InventarioArticulo inventarioArticulo;

}