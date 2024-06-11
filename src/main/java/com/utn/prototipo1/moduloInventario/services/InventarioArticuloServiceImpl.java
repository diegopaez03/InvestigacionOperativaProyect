package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloOrdenCompra.repositories.OrdenDeCompraRepository;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioArticuloServiceImpl extends BaseServicesImpl<InventarioArticulo, Long> implements InventarioArticuloService{

    @Autowired
    private InventarioArticuloRepository inventarioArticuloRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    public InventarioArticuloServiceImpl(BaseRepository<InventarioArticulo, Long> baseRepository) {
        super(baseRepository);
    }


    @Override
    public InventarioArticulo generarInventarioArticulo(long idArticulo) {
        Articulo articulo = articuloRepository.findById(idArticulo).orElseThrow(() -> new EntityNotFoundException("Articulo not found"));

        float cantidad = articulo.;
        float total = cantidad*precio;

        InventarioArticulo inventarioArticulo = new InventarioArticulo();
        inventarioArticulo.setArticulo(articulo);
        inventarioArticulo.setPuntoPedido();//verificar si su modelo es TipoLote
        inventarioArticulo.setStockSeguridad();//y aca verificar cual modelo de los es.
        inventarioArticulo.setLoteOptimo();//aca ver si es de TipoLote

        return inventarioArticulo;
    }
}
