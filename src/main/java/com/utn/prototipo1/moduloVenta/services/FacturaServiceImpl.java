package com.utn.prototipo1.moduloVenta.services;


import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
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

    @Override
    public void actualizarTotalFactura(Long facturaId) {
        Factura factura = obtenerFacturaPorId(facturaId);
        double total = 0.0; // Inicializa el total en 0.0

        // Itera sobre todos los detalles de factura asociados a la factura
        for (DetalleFactura detalle : detalleFacturaRepository.findByFacturaId(facturaId)) {
            total += detalle.getLinea(); // Suma el valor de línea de cada detalle
        }

        factura.setTotal(total); // Establece el total en la factura
        facturaRepository.save(factura); // Guarda la factura actualizada en la base de datos
    }

}


