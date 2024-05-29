package com.utn.prototipo1.moduloOrdenCompra.entities;

import com.utn.prototipo1.BaseEntidad.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "OrdenDeCompra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdenDeCompra extends BaseEntidad {

    private int nroOrdenDeCompra;

    private int tamanoLote;

    @Column(name = "fechaOrdenDeCompra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrdenDeCompra;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "codArticulo")
    private Articulo articulo;


    @NonNull
    @ManyToOne()
    @JoinColumn(name = "codEOC")
    private EstadoOrdenDeCompra estadoOrdenDeCompra;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "codProveedor")
    private Proveedor proveedor;

}
