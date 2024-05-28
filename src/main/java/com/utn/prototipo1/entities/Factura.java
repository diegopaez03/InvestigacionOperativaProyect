package com.utn.prototipo1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Factura extends BaseEntidad {


    private  int nroFactura;

    @Column(name = "fecha_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "nrofactura")
    @Builder.Default
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();

    public void agregarDetalleFactura(DetalleFactura detalleFactura){

        detalleFacturas.add(detalleFactura);
    }

}
