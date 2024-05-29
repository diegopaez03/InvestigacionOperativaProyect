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
@Data
@Builder
public class ArticuloCategoria extends BaseEntidad {


    private int codCategoria;

    private String nombreCategoria;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "codTMI")
    private TipoModeloInventario tipoModeloInventario;


}
