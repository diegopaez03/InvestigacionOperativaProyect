package com.utn.prototipo1.moduloInventario.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
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

    private double puntoPedido;
    private double stockSeguridad;
    private double LoteFijo;
    private double stockActual;
    private double CGI;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "codArticulo")
    private Articulo articulo;
    /*
    public void calcularVariables(Double costoAlmacenamiento, Double desviacionEstandar) {
        if (articulo != null) {
            TipoModeloInventario tipoModeloInventario = articulo.getArticuloCategoria().getTipoModeloInventario();
            ProveedorArticulo proveedorArticulo = articulo.getProveedorArticulo();
            Demanda demanda = articulo.getDemanda();
            if (tipoModeloInventario != null) {
                if ("Lote Fijo".equals(tipoModeloInventario.getNombre())) {
                    if (proveedorArticulo != null) {
                        LoteFijo = Math.sqrt(2*demanda.getCantidad() *(proveedorArticulo.getCostoPedido()/costoAlmacenamiento));
                        puntoPedido = proveedorArticulo.getTiempoDemoraArticulo() * demanda.getCantidad();
                        stockSeguridad = desviacionEstandar * Math.sqrt(proveedorArticulo.getTiempoDemoraArticulo());
                        }
                }
                else if ("Intervalo Fijo".equals(tipoModeloInventario.getNombre())) {
                    if (proveedorArticulo != null) {
                        LoteFijo = Math.sqrt(2*demanda.getCantidad() *(proveedorArticulo.getCostoPedido()/costoAlmacenamiento));
                        stockSeguridad = desviacionEstandar * Math.sqrt(proveedorArticulo.getTiempoDemoraArticulo());
                    }
                }
            }
            CGI = stockActual*demanda.getCantidad() + costoAlmacenamiento* LoteFijo/2 + proveedorArticulo.getCostoPedido() * demanda.getCantidad()/LoteFijo;
        }
    }
    */
    @JsonIgnore
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "codInventario")
    private Inventario inventario;

}