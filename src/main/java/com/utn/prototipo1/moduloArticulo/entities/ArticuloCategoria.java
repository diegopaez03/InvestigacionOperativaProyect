package com.utn.prototipo1.moduloArticulo.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
@Entity
@Table(name = "articuloCategoria")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ArticuloCategoria extends BaseEntidad {

    private String nombreCategoria;
    private String fechaBaja;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "codTMI")
    private TipoModeloInventario tipoModeloInventario;


}
