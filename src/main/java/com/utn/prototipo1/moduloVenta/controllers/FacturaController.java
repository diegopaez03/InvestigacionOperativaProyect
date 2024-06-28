package com.utn.prototipo1.moduloVenta.controllers;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloDemanda.dtos.CrearDemandaDto;
import com.utn.prototipo1.moduloDemanda.services.DemandaService;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.services.DetalleFacturaService;
import com.utn.prototipo1.moduloVenta.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @Autowired
    DemandaService demandaService;

    @Autowired
    DemandaService demandaService;

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
        detalleFactura.setFactura(factura);
        detalleFactura.setArticulo(articulo);
        detalleFactura.calcularLinea(); // Llama al método que calcula el valor de la línea
        detalleFacturaService.save(detalleFactura);
        facturaService.actualizarTotalFactura(facturaId);
        return "redirect:/maestrofactura" ;
    }*/

    @PostMapping("/facturas/{facturaId}/detalles")
    public String crearDetalleFactura(@PathVariable("facturaId") Long facturaId,
                                      @ModelAttribute("detalleFactura") DetalleFactura detalleFactura,
                                      @RequestParam("articulo.id") Long articuloId) {
        Factura factura = facturaService.obtenerFacturaPorId(facturaId);
        Articulo articulo = articuloService.getArticuloById(articuloId);
        detalleFactura.setFactura(factura);
        detalleFactura.setArticulo(articulo);
        detalleFactura.calcularLinea(); // Llama al método que calcula el valor de la línea
        detalleFacturaService.save(detalleFactura);
        facturaService.actualizarTotalFactura(facturaId);

        // Generar la demanda asociada al detalle de factura creado
        CrearDemandaDto crearDemandaDto = new CrearDemandaDto();
        crearDemandaDto.setIdArticulo(articuloId);
        crearDemandaDto.setPeriodoYear(factura.getFechaFactura().getYear());// Puedes ajustar cómo obtienes el año según tu modelo
        demandaService.generarDemanda(crearDemandaDto);
        return "redirect:/maestrofactura";
    }

    @GetMapping("/facturas/{facturaId}/detalles")
    public String verDetallesFactura(@PathVariable("facturaId") Long facturaId, Model model) {
        List<DetalleFactura> detallesFactura = detalleFacturaService.obtenerDetallesPorFactura(facturaId);
        model.addAttribute("detallesFactura", detallesFactura);
        return "MaestroDetalleFactura";
    }
}