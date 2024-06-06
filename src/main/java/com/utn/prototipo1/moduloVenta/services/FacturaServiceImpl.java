package com.utn.prototipo1.moduloVenta.services;


import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.repositories.DetalleFacturaRepository;
import com.utn.prototipo1.moduloVenta.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private  DetalleFacturaRepository detalleFacturaRepository;

    @Override
    public Factura deleteFactura(Long id) {
        facturaRepository.deleteById(id);
        return null;
    }

    @Override
    public Factura obtenerFacturaPorId(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public void crearFactura(Factura factura) {
        facturaRepository.save(factura);
    }

   /* @Override
    public DetalleFactura deleteDetalleFactura(Long id) {
        detalleFacturaRepository.deleteById(id);
        return null;
    }*/


}


