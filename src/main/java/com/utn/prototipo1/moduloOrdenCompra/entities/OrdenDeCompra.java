package com.utn.prototipo1.moduloOrdenCompra.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OrdenDeCompra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdenDeCompra extends BaseEntidad {

    private int tamanoLote;

    @Column(name = "fechaOrdenDeCompra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrdenDeCompra;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idOrdenDeCompra")
    private List<DetalleOrdenCompra> detalleOrdenCompra;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "idEstadoOrdenDeCompra")
    private EstadoOrdenDeCompra estadoOrdenDeCompra;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;

    @PrePersist
    protected void onCreate() {
        this.fechaOrdenDeCompra = new Date();
    }
}
