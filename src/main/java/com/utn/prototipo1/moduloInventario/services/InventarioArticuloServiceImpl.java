package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import com.utn.prototipo1.moduloOrdenCompra.repositories.OrdenDeCompraRepository;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioArticuloServiceImpl  implements InventarioArticuloService{

    @Autowired
    private InventarioArticuloRepository inventarioArticuloRepository;

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
        inventarioArticulo.getCantidad();
        inventarioArticulo.getLoteOptimo();
        inventarioArticulo.getPuntoPedido();
        inventarioArticulo.getStockSeguridad();
        inventarioArticulo.getCGI();
        inventarioArticulo.getCostoAlamcenamiento();
        inventarioArticulo.getCostoPedido();
        return inventarioArticuloRepository.save(inventarioArticulo);

    }

    @Override
        public List<InventarioArticulo> obtenerInventarioArticulos(Long InventarioId) {
        return inventarioArticuloRepository.findByfindByInventarioId(InventarioId);
    }

    @Override
    public InventarioArticulo deleteById(Long InventarioId) {
        inventarioArticuloRepository.deleteById(InventarioId);
        return null;
    }
}
