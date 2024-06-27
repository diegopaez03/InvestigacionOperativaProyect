package com.utn.prototipo1.moduloDemanda.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "PrediccionDemandaMetodo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PrediccionDemandaMetodo extends BaseEntidad {

    private String nombreMetodo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBajaMetodo;

    private Double coeficienteAlpha; // Para Suavización Exponencial

    private Integer numeroPeriodos; // Para Promedio Móvil y Promedio Ponderado

}

