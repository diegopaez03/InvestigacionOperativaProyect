package com.utn.prototipo1.moduloDemanda.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.utn.prototipo1.moduloVenta.services.DetalleFacturaService;
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
    
            for (Factura factura : facturas) {
                for (DetalleFactura detalle : factura.getDetalleFacturas()) {
                    if (detalle.getArticulo().getId().equals(articulo.getId())) {
                        demanda.setCantidad(demanda.getCantidad() + detalle.getCantidad());
                    }
                }
            }
            
            Demanda demandaGenerada = demandaRepository.save(demanda);
            return demandaGenerada;
    }

    /*public Demanda generarDemanda(CrearDemandaDto crearDemandaDto) {
        // Obtener la lista de facturas que corresponden al artículo y período especificados
        List<Factura> facturas = facturaServiceImpl.buscarFacturasFechaArticulo(crearDemandaDto);

        // Obtener el artículo por su ID
        Articulo articulo = articuloRepository.findById(crearDemandaDto.getIdArticulo())
                .orElseThrow(() -> new NoSuchElementException("No se encontró el artículo con ID: " + crearDemandaDto.getIdArticulo()));

        // Inicializar la cantidad total como un float
        float cantidadTotal = 0;

        // Iterar sobre todas las facturas y sus detalles
        for (Factura factura : facturas) {
            for (DetalleFactura detalle : factura.getDetalleFacturas()) {
                // Verificar si el detalle pertenece al artículo deseado
                if (detalle.getArticulo().getId().equals(articulo.getId())) {
                    cantidadTotal += detalle.getCantidad(); // Sumar la cantidad del detalle
                }
            }
        }

        // Crear una nueva instancia de Demanda y establecer los valores
        Demanda demanda = new Demanda();
        demanda.setArticulo(articulo);
        demanda.setPeriodoYear(crearDemandaDto.getPeriodoYear());
        demanda.setCantidad(cantidadTotal);

        // Guardar la demanda en la base de datos
        Demanda demandaGenerada = demandaRepository.save(demanda);
        return demandaGenerada;
    }*/









}
