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

    private double puntoPedido;
    private double stockSeguridad;


    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "codArticulo")
    private Articulo articulo;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "codInventario")
    private Inventario inventario;



}