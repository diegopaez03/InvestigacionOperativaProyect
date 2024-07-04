package com.utn.prototipo1.moduloOrdenCompra.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
import com.utn.prototipo1.moduloInventario.services.InventarioServices;
import com.utn.prototipo1.moduloOrdenCompra.dto.OrdenDeCompraDTO;
import com.utn.prototipo1.moduloOrdenCompra.entities.DetalleOrdenCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.OrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.services.DetalleOrdenCompraService;
import com.utn.prototipo1.moduloOrdenCompra.services.EstadoOrdenCompraService;
import com.utn.prototipo1.moduloOrdenCompra.services.OrdenDeCompraService;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private InventarioServices inventarioServices;

    @Autowired
    private InventarioArticuloService inventarioArticuloService;

    //Métodos de front
    @GetMapping("/{id}")
    public String verOrdenDeCompra(@PathVariable("id") Long id, Model model) throws Exception {
        OrdenDeCompra ordenDeCompra = ordenDeCompraService.findById(id);

        if (ordenDeCompra!= null) {

            float total = 0;
            if (ordenDeCompra.getDetalleOrdenCompra()!= null) {
                for (DetalleOrdenCompra detalle : ordenDeCompra.getDetalleOrdenCompra()) {
                    total += detalle.getTotalDetalleOrdenCompra();
                }
            }

            model.addAttribute("totalOrden", total);
            model.addAttribute("ordenDeCompra", ordenDeCompra);
            return "moduloOrdenCompra/verOrdenDeCompra";
        } else {
            return "redirect:/ordenDeCompra/list";
        }
    }

    @GetMapping("/articulos/{proveedorId}")
    @ResponseBody
    public List<Articulo> getArticulosPorProveedor(@PathVariable Long proveedorId) {
        Proveedor proveedor = proveedorService.getProveedorById(proveedorId);
        return proveedorService.getArticulosOfProveedor(proveedor);
    }

    @GetMapping("/generar")
    public String formularioGenerarOC(Model model){
        Inventario inventario = inventarioServices.obtenerUltimoInventario();

        model.addAttribute("ordenDeCompra", new OrdenDeCompraDTO());
        model.addAttribute("proveedores", proveedorService.getProveedor());
        model.addAttribute("estadoOrdenCompra", estadoOrdenCompraService.getEstadoOrdenCompra());

        model.addAttribute("articulos", articuloService.getAllArticulos());
        model.addAttribute("inventario", inventario);

        return "moduloOrdenCompra/generarOrdenDeCompra";
    }

    @GetMapping("/list")
    public String inicioOrdenCompra(Model model) throws Exception{
        model.addAttribute("orden", ordenDeCompraService.findAll());

        return "moduloOrdenCompra/menuOrdenDeCompra";
    }

    @GetMapping("/{idOrdenDeCompra}/actualizarEstado")
    public String actualizarEstadoOrdenDeCompra(@PathVariable("idOrdenDeCompra") Long idOrdenDeCompra, Model model) throws Exception {
        OrdenDeCompra ordenDeCompra = ordenDeCompraService.findById(idOrdenDeCompra);
        List<EstadoOrdenDeCompra> estadosOrdenDeCompra = estadoOrdenCompraService.getEstadoOrdenCompra();

        model.addAttribute("ordenDeCompra", ordenDeCompra);
        model.addAttribute("estadosOrdenDeCompra", estadosOrdenDeCompra);
        return "moduloOrdenCompra/actualizarEstadoOC";
    }



    //Métodos de funcionamiento
    @Transactional
    @PostMapping("/generar")
    public ModelAndView generarOrdenDeCompra(@ModelAttribute("ordenDeCompra") OrdenDeCompraDTO ordenDeCompraDTO) {
        try {
            Proveedor proveedor = this.proveedorService.getProveedorById(ordenDeCompraDTO.getIdProveedor());

            Long idEstadoOC = Long.parseLong("1");
            EstadoOrdenDeCompra estadoOrdenDeCompra = this.estadoOrdenCompraService.getEstadoOrdenDeCompraById(idEstadoOC);

            OrdenDeCompra ordenDeCompra = new OrdenDeCompra();
            ordenDeCompra.setEstadoOrdenDeCompra(estadoOrdenDeCompra);
            ordenDeCompra.setProveedor(proveedor);

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

            ordenDeCompraService.save(ordenDeCompra);

            return new ModelAndView("redirect:/ordenDeCompra/list");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/actualizarEOC/{id}")
        public ModelAndView actualizarEstadoOrdenDeCompra(@PathVariable("id") Long id, @RequestParam("idEOC") Long idEOC) throws Exception {
            OrdenDeCompra ordenDeCompra = ordenDeCompraService.findById(id);
            EstadoOrdenDeCompra estadoOrdenDeCompra = estadoOrdenCompraService.getEstadoOrdenDeCompraById(idEOC);

            EstadoOrdenDeCompra estadoEntregado = estadoOrdenCompraService.getEstadoOrdenDeCompraByNombre("Entregado");

            if (estadoOrdenDeCompra.getNombreEOC().equals(estadoEntregado.getNombreEOC()) && ordenDeCompra.getEstadoOrdenDeCompra() != estadoEntregado) {
                ordenDeCompra.getDetalleOrdenCompra().forEach(detalle -> {
                    inventarioArticuloService.sumarStock(detalle.getArticulo(), detalle.getCantidad());
                });
            }

            ordenDeCompra.setEstadoOrdenDeCompra(estadoOrdenDeCompra);
            ordenDeCompraService.save(ordenDeCompra);
        return new ModelAndView("redirect:/ordenDeCompra/" + id);
    }

}
