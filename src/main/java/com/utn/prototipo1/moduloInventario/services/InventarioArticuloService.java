package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.services.BaseServices;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;

import java.util.List;

public interface InventarioArticuloService{


    List<InventarioArticulo> findAll();
    InventarioArticulo findById(Long id);
    InventarioArticulo save(InventarioArticulo inventarioArticulo);
    List<InventarioArticulo> obtenerInventarioArticulos(Long InventarioId);
    public InventarioArticulo deleteById(Long InventarioId);
}