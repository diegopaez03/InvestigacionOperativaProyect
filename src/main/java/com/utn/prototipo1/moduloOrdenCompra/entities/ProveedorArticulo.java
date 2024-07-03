package com.utn.prototipo1.moduloOrdenCompra.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ProveedorArticulo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProveedorArticulo extends BaseEntidad {

    @Column(name = "tiempoDemoraArticulo")
    private int tiempoDemoraArticulo;

    @Column
    private double costoPedido;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;
}
