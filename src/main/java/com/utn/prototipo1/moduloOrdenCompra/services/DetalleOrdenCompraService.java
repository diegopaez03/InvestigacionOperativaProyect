package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.DetalleOrdenCompra;
import com.utn.prototipo1.moduloOrdenCompra.repositories.DetalleOrdenCompraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenCompraService extends BaseServicesImpl<DetalleOrdenCompra, Long> implements IDetalleOrdenCompraService {

    @Autowired
    private DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    public DetalleOrdenCompraService(BaseRepository<DetalleOrdenCompra, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public DetalleOrdenCompra generarDetalle(float cantidad, long idArticulo) {
        Articulo articulo = articuloRepository.findById(idArticulo).orElseThrow(() -> new EntityNotFoundException("Articulo not found"));

        float precio = articulo.getPrecio();
        float total = cantidad*precio;

        DetalleOrdenCompra detalleOrdenCompra = new DetalleOrdenCompra();
        detalleOrdenCompra.setArticulo(articulo);
        detalleOrdenCompra.setCantidad(cantidad);
        detalleOrdenCompra.setTotalDetalleOrdenCompra(total);

        return detalleOrdenCompra;
    }
}
