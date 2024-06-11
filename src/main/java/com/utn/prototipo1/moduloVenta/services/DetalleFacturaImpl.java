package com.utn.prototipo1.moduloVenta.services;

import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.repositories.DetalleFacturaRepository;
import com.utn.prototipo1.moduloVenta.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DetalleFacturaImpl implements DetalleFacturaService{

   @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Override
    public List<DetalleFactura> findAll() {
        return detalleFacturaRepository.findAll();
    }

    @Override
    public DetalleFactura findById(Long id) {
        return detalleFacturaRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleFactura save(DetalleFactura detalleFactura) {
        detalleFactura.getLinea();
        return detalleFacturaRepository.save(detalleFactura);

    }

    @Override
    public List<DetalleFactura> obtenerDetallesPorFactura(Long facturaId) {
        return detalleFacturaRepository.findByFacturaId(facturaId);
    }

    @Override
    public DetalleFactura deleteById(Long detalleId) {
        detalleFacturaRepository.deleteById(detalleId);
        return null;
    }

}
