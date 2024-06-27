package com.utn.prototipo1.moduloDemanda.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "PrediccionDemanda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PrediccionDemanda extends BaseEntidad {

    /*@Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesde;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;*/

    private double demandaPredicha;

    private String demandaReal; // Opcional, para comparación posterior


   /* @ManyToOne() //REVISAARRR
    @JoinColumn(name = "codArticulo")
    private Articulo articulo;*/

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_prediccion")
    private List<Demanda> Demandas = new ArrayList<>();


  /* @ManyToOne() //REVISAARRR
    @JoinColumn(name = "codParametroPredic")
    private ParametrosPrediccion parametrosPrediccion;


    @ManyToOne() //REVISAARRR
    @JoinColumn(name = "codPredicDemandaMetodo")
    private PrediccionDemandaMetodo prediccionDemandaMetodo;*/


}
