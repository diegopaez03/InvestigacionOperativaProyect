package com.utn.prototipo1.moduloDemanda.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloDemanda.dtos.CrearDemandaDto;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.repositories.DemandaRepository;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;

@Service
public class DemandaService extends BaseServicesImpl<Demanda, Long> implements IDemandaService {

    @Autowired
    private DemandaRepository demandaRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    public DemandaService(BaseRepository<Demanda, Long> baseRepository) {
        super(baseRepository);
    }

    public Demanda generarDemanda(CrearDemandaDto crearDemandaDto) {
    Demanda demanda = new Demanda();
    try {
        List<Factura> facturas = demandaRepository.findFacturasByFechaAndArticulo(crearDemandaDto.getFechaDesde(), crearDemandaDto.getFechaHasta(), crearDemandaDto.getIdArticulo());
        
        Articulo articulo = articuloRepository.findById(crearDemandaDto.getIdArticulo())
            .orElseThrow(() -> new NoSuchElementException("No se encontró el artículo con ID: " + crearDemandaDto.getIdArticulo()));

        demanda.setArticulo(articulo);
        demanda.setFechaDesde(crearDemandaDto.getFechaDesde());
        demanda.setFechaHasta(crearDemandaDto.getFechaHasta());

        float cantidadArticulos = 0;
        for (Factura factura : facturas) {
            for (DetalleFactura detalle : factura.getDetalleFacturas()) {
                if (detalle.getArticulo().equals(articulo)) {
                    cantidadArticulos += detalle.getCantidad();
                }
            }
        }

        demanda.setCantidad(cantidadArticulos);

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        // Manejo de excepciones adicional si es necesario
    }

    return demandaRepository.save(demanda);
    }

}
