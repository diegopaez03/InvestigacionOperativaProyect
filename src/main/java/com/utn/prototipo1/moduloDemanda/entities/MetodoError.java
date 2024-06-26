package com.utn.prototipo1.moduloDemanda.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "MetodoError")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MetodoError extends BaseEntidad {

    private String nombreMetodoError;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBajaMetodoError;


}
