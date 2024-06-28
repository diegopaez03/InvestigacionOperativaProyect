package com.utn.prototipo1.moduloInventario.entities;

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

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "codInventario")
    private Inventario inventario;

}