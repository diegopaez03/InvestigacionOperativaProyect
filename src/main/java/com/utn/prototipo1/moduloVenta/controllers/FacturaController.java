package com.utn.prototipo1.moduloVenta.controllers;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloDemanda.dtos.CrearDemandaDto;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.services.DemandaService;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.services.DetalleFacturaService;
import com.utn.prototipo1.moduloVenta.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private InventarioArticuloService inventarioArticuloService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @Autowired
    DemandaService demandaService;

    @Autowired
    private InventarioArticuloRepository inventarioArticuloRepository;

    @GetMapping("/maestrofactura")
    public String mostrarTodasLasFacturas(Model model) {
        model.addAttribute("facturas", facturaService.obtenerTodasLasFacturas());
        return "MaestroFactura";
    }

    @GetMapping("/maestrofactura/nuevo")
    public String mostrarFormularioCrearFactura(Model model) {
        Factura factura = new Factura();
        factura.getDetalleFacturas().add(new DetalleFactura()); // Agrega un detalle de factura por defecto
        model.addAttribute("factura", factura);
        model.addAttribute("articulos", articuloService.getAllArticulos());
        return "crear-factura";
    }

    @PostMapping("/facturas")
    public String crearFactura(@ModelAttribute("factura") Factura factura) {
        factura.setFechaFactura(new Date()); // Asignar la fecha y hora actual
        for (DetalleFactura detalle : factura.getDetalleFacturas()) {
            detalle.setFactura(factura); // Establece la relación entre la factura y sus detalles
        }
        facturaService.crearFactura(factura);
        return "redirect:/maestrofactura";
    }

    @GetMapping("/maestrofactura/{id}")
    public String eliminarFactura(@PathVariable("id") Long id) {
        facturaService.deleteFactura(id);
        return "redirect:/maestrofactura";
    }

    @GetMapping("/facturas/{facturaId}/detalles/nuevo")
    public String mostrarFormularioCrearDetalle(@PathVariable("facturaId") Long facturaId, Model model) {
        Factura factura = facturaService.obtenerFacturaPorId(facturaId);
        List<Articulo> articulos = articuloService.getAllArticulos();
        DetalleFactura detalleFactura = new DetalleFactura();
        detalleFactura.setFactura(factura);
        model.addAttribute("detalleFactura", detalleFactura);
        model.addAttribute("articulos", articulos);
        model.addAttribute("factura", factura);
        return "crear-detalle-factura";
    }


    /*@PostMapping("/facturas/{facturaId}/detalles")
    public String crearDetalleFactura(@PathVariable("facturaId") Long facturaId,
                                      @ModelAttribute("detalleFactura") DetalleFactura detalleFactura,
                                      @RequestParam("articulo.id") Long articuloId) {
        Factura factura = facturaService.obtenerFacturaPorId(facturaId);
        Articulo articulo = articuloService.getArticuloById(articuloId);

        // Buscar si ya existe un detalle con el mismo artículo en la factura
        DetalleFactura detalleExistente = factura.getDetalleFacturas().stream()
                .filter(d -> d.getArticulo().getId().equals(articuloId))
                .findFirst()
                .orElse(null);

        if (detalleExistente != null) {
            // Si ya existe, sumar la cantidad
            detalleExistente.setCantidad(detalleExistente.getCantidad() + detalleFactura.getCantidad());
            detalleExistente.calcularLinea(); // Recalcular el valor de la línea
            detalleFacturaService.save(detalleExistente);
        } else {
            // Si no existe, crear un nuevo detalle
            detalleFactura.setFactura(factura);
            detalleFactura.setArticulo(articulo);
            detalleFactura.calcularLinea(); // Llama al método que calcula el valor de la línea
            detalleFacturaService.save(detalleFactura);
        }

        facturaService.actualizarTotalFactura(facturaId);

        // Generar la demanda asociada al detalle de factura creado
        CrearDemandaDto crearDemandaDto = new CrearDemandaDto();
        crearDemandaDto.setIdArticulo(articuloId);
        crearDemandaDto.setPeriodoYear(Integer.parseInt(factura.getFechaFactura().toString().substring(0,4)));// Puedes ajustar cómo obtienes el año según tu modelo
        demandaService.generarDemanda(crearDemandaDto);
        return "redirect:/maestrofactura";
    }*/

    @PostMapping("/facturas/{facturaId}/detalles")
    public String crearDetalleFactura(@PathVariable("facturaId") Long facturaId,
                                      @ModelAttribute("detalleFactura") DetalleFactura detalleFactura,
                                      @RequestParam("articulo.id") Long articuloId) {
        Factura factura = facturaService.obtenerFacturaPorId(facturaId);
        Articulo articulo = articuloService.getArticuloById(articuloId);

        // Buscar si ya existe un detalle con el mismo artículo en la factura
        DetalleFactura detalleExistente = factura.getDetalleFacturas().stream()
                .filter(d -> d.getArticulo().getId().equals(articuloId))
                .findFirst()
                .orElse(null);

        if (detalleExistente != null) {
            // Si ya existe, sumar la cantidad
            detalleExistente.setCantidad(detalleExistente.getCantidad() + detalleFactura.getCantidad());
            detalleExistente.calcularLinea(); // Recalcular el valor de la línea
            detalleFacturaService.save(detalleExistente);
        } else {
            // Si no existe, crear un nuevo detalle
            detalleFactura.setFactura(factura);
            detalleFactura.setArticulo(articulo);
            detalleFactura.calcularLinea(); // Llama al método que calcula el valor de la línea
            detalleFacturaService.save(detalleFactura);
            InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findByArticulo(articulo);

        }

        facturaService.actualizarTotalFactura(facturaId);
        facturaService.actualizarStockPorDetalleFactura(detalleFactura);
        facturaService.crearFactura(factura);



        // Generar la demanda asociada al detalle de factura creado
        CrearDemandaDto crearDemandaDto = new CrearDemandaDto();
        crearDemandaDto.setIdArticulo(articuloId);
        crearDemandaDto.setPeriodoYear(Integer.parseInt(factura.getFechaFactura().toString().substring(0, 4))); // Puedes ajustar cómo obtienes el año según tu modelo

        // Configurar la URL del endpoint de demanda
        String demandaEndpoint = "http://localhost:8080/restDemanda"; // Ajusta la URL según tu configuración

        // Configurar RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Configurar el cuerpo de la solicitud (CrearDemandaDto como JSON)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CrearDemandaDto> request = new HttpEntity<>(crearDemandaDto, headers);

        // Realizar la solicitud POST al endpoint de demanda
        Demanda demandaCreada = restTemplate.postForObject(demandaEndpoint, request, Demanda.class);

        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findByArticulo(articulo);
        inventarioArticuloService.calcularVariables(inventarioArticulo.getId());


        // Continuar con tu lógica actual después de crear la demanda
        return "redirect:/maestrofactura";
    }



    @GetMapping("/facturas/{facturaId}/detalles")
    public String verDetallesFactura(@PathVariable("facturaId") Long facturaId, Model model) {
        List<DetalleFactura> detallesFactura = detalleFacturaService.obtenerDetallesPorFactura(facturaId);
        model.addAttribute("detallesFactura", detallesFactura);
        return "MaestroDetalleFactura";
    }
}