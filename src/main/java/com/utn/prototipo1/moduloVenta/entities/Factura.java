package com.utn.prototipo1.moduloVenta.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Factura extends BaseEntidad {


    private  Long nroFactura;

    @Column(name = "fecha_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;

    private double total;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "nrofactura")
    @Builder.Default
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();

    public void addDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFacturas.add(detalleFactura);
        detalleFactura.setFactura(this);
        calcularTotal();
    }

    public void calcularTotal() {
        total = detalleFacturas.stream()
                .mapToDouble(DetalleFactura::getLinea)
                .sum();
    }

}
