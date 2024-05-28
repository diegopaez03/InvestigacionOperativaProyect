package com.utn.prototipo1.moduloArticulo.entities;

import com.utn.prototipo1.BaseEntidad.BaseEntidad;
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
@Data
@Builder
public class Articulo extends BaseEntidad {


    private int codArticulo;

    private int loteOptimo;

    private String nombreArticulo;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "codCategoria")
    private ArticuloCategoria articuloCategoria;


}
