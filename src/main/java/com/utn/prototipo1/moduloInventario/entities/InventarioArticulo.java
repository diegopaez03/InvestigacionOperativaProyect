package com.utn.prototipo1.moduloInventario.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "inventarioArticulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InventarioArticulo extends BaseEntidad {

    private int puntoPedido;
    private int LoteOptimo;
    private int stockSeguridad;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;
}
