package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.services.BaseServices;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;

import java.util.List;

public interface InventarioArticuloService{


    List<InventarioArticulo> findAll();
    InventarioArticulo findById(Long id);
    InventarioArticulo save(InventarioArticulo inventarioArticulo);
    List<InventarioArticulo> obtenerArticuloPorInventario(Long InventarioId);
    public InventarioArticulo deleteById(Long InventarioId);
    public void sumarStock(Articulo articulo, int cantidad);
    public void calcularVariables(Long inventarioArticuloId, Double costoAlmacenamiento, Double desviacion);
    public void restarStock(Articulo articulo, int cantidad);
}