package com.utn.prototipo1.moduloDemanda.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ParametrosPrediccion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ParametrosPrediccion extends BaseEntidad {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    private float errorAceptable;

    private int cantidadPeriodos;

    @ManyToOne()
    @JoinColumn(name = "codMetodoError")
    private MetodoError metodoError;

}

