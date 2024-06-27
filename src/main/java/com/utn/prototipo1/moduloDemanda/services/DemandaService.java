package com.utn.prototipo1.moduloDemanda.services;

import java.util.ArrayList;
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
import com.utn.prototipo1.moduloVenta.services.FacturaServiceImpl;

@Service
public class DemandaService extends BaseServicesImpl<Demanda, Long> implements IDemandaService {

    @Autowired
    private DemandaRepository demandaRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private FacturaServiceImpl facturaServiceImpl;

    public DemandaService(BaseRepository<Demanda, Long> baseRepository) {
        super(baseRepository);
    }

    public List<Demanda> getDemandasByArticulo(Long idArticulo) {
        Articulo articulo = articuloRepository.findById(idArticulo)
        .orElseThrow(() -> new NoSuchElementException("No se encontró el artículo con ID: " + idArticulo));
        return demandaRepository.findAllByArticulo(articulo);
    }

    public List<Demanda> getDemandasByYear(int periodoYear) {
        return demandaRepository.findAllByPeriodoYear(periodoYear);
    }

    public Demanda generarDemanda(CrearDemandaDto crearDemandaDto) {
        Demanda demanda = new Demanda();
        List<Factura> facturas = facturaServiceImpl.buscarFacturasFechaArticulo(crearDemandaDto);
        
        Articulo articulo = articuloRepository.findById(crearDemandaDto.getIdArticulo())
            .orElseThrow(() -> new NoSuchElementException("No se encontró el artículo con ID: " + crearDemandaDto.getIdArticulo()));

        demanda.setArticulo(articulo);
        demanda.setPeriodoYear(crearDemandaDto.getPeriodoYear());
        demanda.setCantidad(0); // Inicializa la cantidad en 0

        List<Factura> facturasUsadas = new ArrayList<Factura>();
        for (Factura factura : facturas) {
            for (DetalleFactura detalle : factura.getDetalleFacturas()) {
                if (detalle.getArticulo().getId().equals(articulo.getId())) {
                    demanda.setCantidad(demanda.getCantidad() + detalle.getCantidad());
                    facturasUsadas.add(factura);
                }
            }
        }
        demanda.setFacturas(facturasUsadas);

        Demanda demandaGenerada = demandaRepository.save(demanda);
        return demandaGenerada;
    }
    

}
