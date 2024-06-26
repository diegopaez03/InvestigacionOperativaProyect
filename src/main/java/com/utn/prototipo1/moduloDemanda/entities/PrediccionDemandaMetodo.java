package com.utn.prototipo1.moduloDemanda.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class PrediccionDemandaMetodo extends BaseEntidad {

    private String nombreMetodo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBajaMetodo;

}

