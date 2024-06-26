package com.utn.prototipo1.moduloOrdenCompra.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "EstadoOrdenCompra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EstadoOrdenDeCompra extends BaseEntidad {

    private String nombreEOC;

    @Column(name = "fecha_baja", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
}
