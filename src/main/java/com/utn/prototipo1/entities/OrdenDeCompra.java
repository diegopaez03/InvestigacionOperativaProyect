package com.utn.prototipo1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name = "ordenDecompra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class OrdenDeCompra extends BaseEntidad {

    private int nroOrdenDeCompra;

    private int tamanoLote;

    @Column(name = "fecha_OC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrdenDeCompra;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "codArticulo")
    private Articulo articulo;


    @NotNull
    @ManyToOne()
    @JoinColumn(name = "codEOC")
    private EstadoOrdenDeCompra estadoOrdenDeCompra;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "codProveedor")
    private Proveedor proveedor;

}
