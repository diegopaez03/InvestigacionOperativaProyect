package com.utn.prototipo1.moduloInventario.services;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;

import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.repositories.ProveedorArtículoRepository;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorArticuloService;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioArticuloServiceImpl  implements InventarioArticuloService{

    @Autowired
    private InventarioArticuloRepository inventarioArticuloRepository;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    InventarioRepository inventarioRepository;


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

    public void actualizarStock(Articulo articulo, int cantidad) {

        Articulo articuloExistente = articuloRepository.findById(articulo.getId()).orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findByArticulo(articuloExistente);
        if (inventarioArticulo == null) {

            inventarioArticulo = new InventarioArticulo();
            inventarioArticulo.setArticulo(articuloExistente);

            Inventario inventario = inventarioRepository.findById(1L).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
            inventarioArticulo.setInventario(inventario);
            inventarioArticulo.setStockActual(0);
        }

        inventarioArticulo.setStockActual(inventarioArticulo.getStockActual() + cantidad);
        inventarioArticuloRepository.save(inventarioArticulo);
    }

    public void calcularVariables(Long inventarioArticuloId, String tipoModeloInventario, Double costoAlmacenamiento, Double desviacion) {
        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.getReferenceById(inventarioArticuloId);
        if ("Lote Fijo".equals(tipoModeloInventario)) {

            double costoPedido = proveedorService.getProveedorArticuloConMenorDemora(inventarioArticulo.getArticulo().getId()).getTiempoDemoraArticulo();
            double lotefijo = costoPedido * costoAlmacenamiento;
            double stock = desviacion * Math.sqrt(proveedorService.getProveedorArticuloConMenorDemora(inventarioArticulo.getArticulo().getId()).getTiempoDemoraArticulo());
            inventarioArticulo.setLoteFijo(lotefijo);
            inventarioArticulo.setPuntoPedido(costoPedido);
            inventarioArticulo.setStockSeguridad(stock);


        } else if ("Intervalo Fijo".equals(tipoModeloInventario)) {

            double stock = desviacion * Math.sqrt(proveedorService.getProveedorArticuloConMenorDemora(inventarioArticulo.getArticulo().getId()).getTiempoDemoraArticulo());
            inventarioArticulo.setStockSeguridad(stock);

        } else {
            throw new IllegalArgumentException("Tipo de modelo de inventario no reconocido");
        }

        inventarioArticuloRepository.save(inventarioArticulo);
    }

}