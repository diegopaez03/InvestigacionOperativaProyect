package com.utn.prototipo1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "inventarioArticulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class InventarioArticulo extends BaseEntidad {

    private int puntoPedido;
    private int stockActual;
    private int stockSeguridad;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "codArticulo")
    private Articulo articulo;
}
