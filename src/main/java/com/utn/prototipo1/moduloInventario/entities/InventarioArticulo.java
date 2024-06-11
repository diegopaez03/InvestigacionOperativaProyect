package com.utn.prototipo1.moduloInventario.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "inventarioArticulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InventarioArticulo extends BaseEntidad {

    private double puntoPedido;
    private double LoteOptimo;
    private double stockSeguridad;
    private double CGI;
    private int cantidad;
    private int CostoAlamcenamiento;
    private int CostoPedido;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "CodInventario")
    private Inventario inventario;

  /* public double getLoteOptimo(){
        return LoteOptimo = Math.sqrt(2*cantidad*(CostoPedido/CostoAlamcenamiento));
    }
    public double getCGI(){
        return CGI = (articulo.getPrecio()*cantidad) + (CostoAlamcenamiento*(LoteOptimo/2))+ (CostoPedido*(cantidad/LoteOptimo));
    }

    public void calcularLoteOptimo(){
        this.LoteOptimo = Math.sqrt(2*this.cantidad*(this.CostoPedido/this.CostoAlamcenamiento));
    }
    public void cacularCGI(){
        this.CGI = (this.articulo.getPrecio()*this.cantidad) + (this.CostoAlamcenamiento*(this.LoteOptimo/2))+ (this.CostoPedido*(this.cantidad/this.LoteOptimo));
    }
*/
}
