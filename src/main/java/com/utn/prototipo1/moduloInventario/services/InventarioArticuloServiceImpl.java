package com.utn.prototipo1.moduloInventario.services;


import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;

import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.repositories.ProveedorArtículoRepository;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioArticuloServiceImpl  implements InventarioArticuloService{

    @Autowired
    private InventarioArticuloRepository inventarioArticuloRepository;
    @Autowired
    private ProveedorArticuloService proveedorArticuloService;


    @Override
    public List<InventarioArticulo> findAll() {
        return inventarioArticuloRepository.findAll();
    }

    @Override
    public InventarioArticulo findById(Long id) {
        return inventarioArticuloRepository.findById(id).orElse(null);
    }

    @Override
    public InventarioArticulo save(InventarioArticulo inventarioArticulo) {
        return inventarioArticuloRepository.save(inventarioArticulo);

    }

    @Override
        public List<InventarioArticulo> obtenerArticuloPorInventario(Long InventarioId) {
        return inventarioArticuloRepository.findByInventarioId(InventarioId);
    }



    @Override
    public InventarioArticulo deleteById(Long InventarioId) {
        inventarioArticuloRepository.deleteById(InventarioId);
        return null;
    }
    public void actualizarStock(Articulo articulo, int cantidad){

    }

    public void calcularVariables(Long inventarioArticuloId, String tipoModeloInventario, Double costoAlmacenamiento, Double desviacion) {
        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.getReferenceById(inventarioArticuloId);
        if ("Lote Fijo".equals(tipoModeloInventario)) {

            Double costoPedido = proveedorArticuloService.obtenercostoPedido(inventarioArticulo.getArticulo().getId());
            Double lotefijo = costoPedido * costoAlmacenamiento;
            Double stock = desviacion * Math.sqrt(proveedorArticuloService.obtenertiempodemora(inventarioArticulo.getArticulo().getId()));
            inventarioArticulo.setLoteFijo(lotefijo);
            inventarioArticulo.setPuntoPedido(costoPedido);
            inventarioArticulo.setStockSeguridad(stock);


        } else if ("Intervalo Fijo".equals(tipoModeloInventario)) {

            Double stock = desviacion * Math.sqrt(proveedorArticuloService.obtenertiempodemora(inventarioArticulo.getArticulo().getId()));
            inventarioArticulo.setStockSeguridad(stock);

        } else {
            throw new IllegalArgumentException("Tipo de modelo de inventario no reconocido");
        }

        inventarioArticuloRepository.save(inventarioArticulo);
    }

}