package com.utn.prototipo1.moduloVenta.controllers;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
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
    private  ArticuloService articuloService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    //Listar las facturas que se crean
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
        model.addAttribute("articulos", articuloService.getArticulo());
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


    //borrar la factura
    @GetMapping("/maestrofactura/{id}")
    public String eliminarFactura(@PathVariable Long id){
        facturaService.deleteFactura(id);
        return "redirect:/maestrofactura";
    }


    //DETALLE FACTURA ------------------------------

    @GetMapping("/facturas/{facturaId}/detalles/nuevo")
    public String mostrarFormularioCrearDetalle(@PathVariable("facturaId") Long facturaId, Model model) {
        Factura factura = facturaService.obtenerFacturaPorId(facturaId);
        List<Articulo> articulos = articuloService.getArticulo();
        DetalleFactura detalleFactura = new DetalleFactura();
        detalleFactura.setFactura(factura);
        model.addAttribute("detalleFactura", detalleFactura);
        model.addAttribute("articulos", articulos);
        model.addAttribute("factura", factura);
        return "crear-detalle-factura";
    }

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
        return "redirect:/maestrofactura" ;
    }


    @GetMapping("/facturas/{facturaId}/detalles")
    public String verDetallesFactura(@PathVariable Long facturaId, Model model) {
        List<DetalleFactura> detallesFactura = detalleFacturaService.obtenerDetallesPorFactura(facturaId);
        model.addAttribute("detallesFactura", detallesFactura);
        return "MaestroDetalleFactura";
    }



}


