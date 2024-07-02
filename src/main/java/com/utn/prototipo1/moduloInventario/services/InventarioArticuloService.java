package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.services.BaseServices;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;

import java.util.List;

public interface InventarioArticuloService{


    List<InventarioArticulo> findAll();
    InventarioArticulo findById(Long id);
    InventarioArticulo save(InventarioArticulo inventarioArticulo);
    List<InventarioArticulo> obtenerArticuloPorInventario(Long InventarioId);
    public List<InventarioArticulo> obtenerInventarioArticulosConStockBajo();
    public InventarioArticulo deleteById(Long InventarioId);
    public void sumarStock(Articulo articulo, double cantidad);
    public void calcularVariables(Long inventarioArticuloId);
    public void restarStock(Articulo articulo, int cantidad);
    public void actualizarStockPorFactura(Factura factura);

    //Hecho por diego
    public List<InventarioArticulo> getInventariosByArticulo(Long idArticulo);
}