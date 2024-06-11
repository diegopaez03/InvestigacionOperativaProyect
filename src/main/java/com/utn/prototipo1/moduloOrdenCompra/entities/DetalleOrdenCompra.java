package com.utn.prototipo1.moduloOrdenCompra.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DetalleOrdenCompra")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetalleOrdenCompra extends BaseEntidad {

    @Column(name = "cantidad")
    private float cantidad;

    @Column(name = "totalDetalleOrdenCompra")
    private float totalDetalleOrdenCompra;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;
}
