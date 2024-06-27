package com.utn.prototipo1.moduloDemanda.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "demanda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Demanda  extends BaseEntidad {


    private float cantidad;

    private int periodoYear;

    @ManyToOne() //REVISAARRR
    @JoinColumn(name = "codArticulo")
    private Articulo articulo;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_demanda")
    @Builder.Default
    private List<Factura> Facturas = new ArrayList<>();

}
