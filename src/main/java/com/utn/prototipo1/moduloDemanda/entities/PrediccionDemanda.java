package com.utn.prototipo1.moduloDemanda.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrediccionDemanda extends BaseEntidad {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesde;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_prediccion")
    private List<Demanda> Demandas = new ArrayList<>();



    @OneToOne() //REVISAARRR
    @JoinColumn(name = "codParametroPredic")
    private ParametrosPrediccion parametrosPrediccion;


    @OneToOne() //REVISAARRR
    @JoinColumn(name = "codParametroPredicMetodo")
    private PrediccionDemandaMetodo prediccionDemandaMetodo;
}
