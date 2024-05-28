package com.utn.prototipo1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name = "tipoModeloInventario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class TipoModeloInventario extends BaseEntidad{


    private int codTMI;

    private String nombreTMI;

    @Column(name = "fecha_bajaTMI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBajaTMI;

}
