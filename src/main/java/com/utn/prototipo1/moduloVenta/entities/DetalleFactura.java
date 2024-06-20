package com.utn.prototipo1.moduloVenta.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "detalleFactura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DetalleFactura extends BaseEntidad {

    private int cantidad;
    private int linea;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "codArticulo")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "nrofactura")
    private Factura factura;

    public double getLinea() {
        return cantidad * articulo.getPrecioVenta();
    }

    public void calcularLinea() {
        this.linea = this.cantidad * this.articulo.getPrecioVenta();
    }


}
