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
public class ProveedorArticulo extends BaseEntidad {

    @Column(name = "tiempoDemoraArticulo")
    private int tiempoDemoraArticulo;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "codArticulo")
    private Articulo articulo;
}
