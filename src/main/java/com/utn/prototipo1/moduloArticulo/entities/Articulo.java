package com.utn.prototipo1.moduloArticulo.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name = "articulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Articulo extends BaseEntidad {



    private String nombreArticulo;


    private float precioCompra;

    private int precioVenta;

    private String fechaBaja;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "codCategoria")
    private ArticuloCategoria articuloCategoria;


}