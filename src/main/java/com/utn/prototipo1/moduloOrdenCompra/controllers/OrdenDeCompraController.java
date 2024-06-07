package com.utn.prototipo1.moduloOrdenCompra.controllers;

import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloOrdenCompra.dto.OrdenDeCompraDTO;
import com.utn.prototipo1.moduloOrdenCompra.entities.DetalleOrdenCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.OrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.services.DetalleOrdenCompraService;
import com.utn.prototipo1.moduloOrdenCompra.services.EstadoOrdenCompraService;
import com.utn.prototipo1.moduloOrdenCompra.services.OrdenDeCompraService;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("ordenDeCompra")
@CrossOrigin(origins = "*")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraService ordenDeCompraService;

    @Autowired
    private DetalleOrdenCompraService detalleOrdenCompraService;

    @Autowired
    private EstadoOrdenCompraService estadoOrdenCompraService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/generar")
    public String formularioGenerarOC(Model model){
        model.addAttribute("ordenDeCompra", new OrdenDeCompraDTO());
        model.addAttribute("proveedores", proveedorService.getProveedor());
        model.addAttribute("estadoOrdenCompra", estadoOrdenCompraService.getEstadoOrdenCompra());
        model.addAttribute("articulos", articuloService.getArticulo());
        return "moduloOrdenCompra/generarOrdenDeCompra";
    }

    @PostMapping("/generar")
    public OrdenDeCompra generarOrdenDeCompra(@ModelAttribute("ordenDeCompra") OrdenDeCompraDTO ordenDeCompraDTO) {
        try {
            Proveedor proveedor = this.proveedorService.getProveedorById(ordenDeCompraDTO.getIdProveedor());

            EstadoOrdenDeCompra estadoOrdenDeCompra = this.estadoOrdenCompraService.getEstadoOrdenDeCompraById(ordenDeCompraDTO.getIdEOC());

            OrdenDeCompra ordenDeCompra = new OrdenDeCompra();
            ordenDeCompra.setEstadoOrdenDeCompra(estadoOrdenDeCompra);
            ordenDeCompra.setProveedor(proveedor);
            ordenDeCompra.setTamanoLote(ordenDeCompraDTO.getTamanoLote());

            List<DetalleOrdenCompra> detalleOrdenCompras = new ArrayList<>();
            ordenDeCompraDTO.getDetalles().forEach(detalle -> {
                DetalleOrdenCompra detalleOC = detalleOrdenCompraService.generarDetalle(detalle.getCantidad(), detalle.getIdArticulo());

                DetalleOrdenCompra detalleOrdenCompra = null;
                try {
                    detalleOrdenCompra = detalleOrdenCompraService.save(detalleOC);
                    detalleOrdenCompras.add(detalleOrdenCompra);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            ordenDeCompra.setDetalleOrdenCompra(detalleOrdenCompras);

            return ordenDeCompraService.save(ordenDeCompra);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
