package com.utn.prototipo1.moduloInventario.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.hibernate.annotations.NotFound;

@Entity
@Table(name = "inventarioArticulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InventarioArticulo extends BaseEntidad {


    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "idInventario")
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
