package com.utn.prototipo1.moduloInventario.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
import com.utn.prototipo1.moduloInventario.services.InventarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class InventarioController {

    @Autowired
    private InventarioServices inventarioServices;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private InventarioArticuloService inventarioArticuloService;

    @GetMapping("/maestroinventario")
    public String mostrarTodosLosInventarios(Model model) {
        List<Inventario> inventarios = inventarioServices.findAll();
        model.addAttribute("inventarios", inventarios);

        if (!inventarios.isEmpty()) {
            // Redirige al último inventario creado
            Inventario ultimoInventario = inventarios.get(inventarios.size() - 1);
            return "redirect:/inventarios/" + ultimoInventario.getId() + "/inventarioArticulos";
        }

        // Redirige a la página de error si no hay inventarios
        return "errorNoInventarios";
    }



    @GetMapping("/maestroinventario/nuevo")
    public String mostrarFormularioCrearInventario(Model model) {
        Inventario inventario = new Inventario();
        inventario.getInventarioArticulos().add(new InventarioArticulo());
        model.addAttribute("inventario", inventario);
        model.addAttribute("articulos", articuloService.getAllArticulos());
        return "crear-inventario";
    }

    @PostMapping("/inventarios")
    public String crearInventario(@ModelAttribute("inventario") Inventario inventario) {
        inventario.setFechaDesde(LocalDate.now());
        for (InventarioArticulo inventarioArticulo : inventario.getInventarioArticulos()) {
            inventarioArticulo.setInventario(inventario);
            inventario.getInventarioArticulos().add(inventarioArticulo);
        }
        inventarioServices.crearInventario(inventario);
        return "redirect:/maestroinventario";
    }

    @GetMapping("/maestroinventario/{id}")
    public String eliminarInventario(@PathVariable("id") Long id) {
        inventarioServices.deleteById(id);
        return "redirect:/maestroinventario";
    }

    @PostMapping("/inventarios/{inventarioId}/inventarioArticulos")
    public String crearinventarioArticulo(@PathVariable("inventarioId") Long inventarioId,
                                          @ModelAttribute("inventarioArticulo") InventarioArticulo inventarioArticulo,
                                          @RequestParam("articulo.id") Long articuloId) {
        Inventario inventario = inventarioServices.obtenerInventarioPorId(inventarioId);
        Articulo articulo = articuloService.getArticuloById(articuloId);
        inventarioArticulo.setInventario(inventario);
        inventarioArticulo.setArticulo(articulo);
        inventario.getInventarioArticulos().add(inventarioArticulo);
        inventarioArticuloService.save(inventarioArticulo);
        return "redirect:/maestroinventario";
    }

    @GetMapping("/inventarios/{inventarioId}/inventarioArticulos")
    public String verInventarioArticulo(@PathVariable("inventarioId") Long inventarioId, Model model) {
        List<InventarioArticulo> inventarioArticulos = inventarioArticuloService.obtenerArticuloPorInventario(inventarioId);
        model.addAttribute("inventarioArticulos", inventarioArticulos);
        return "MaestroInventarioArticulo";
    }

    @GetMapping("/inventarios/{inventarioId}/inventarioArticulos/VistaValoresCalculados/{id}")
    public String mostrarValoresCalculados(@PathVariable("inventarioId") Long inventarioId,
                                           @PathVariable("id") Long id,
                                           Model model) {
        InventarioArticulo inventarioArticulo = inventarioArticuloService.findById(id);

        if (inventarioArticulo != null) {
            model.addAttribute("inventarioArticulo", inventarioArticulo);
            return "VistaValoresCalculados";
        } else {
            return "redirect:/maestroinventario";
        }
    }
    @GetMapping("/inventarios/stockBajo")
    public String mostrarInventarioArticulosConStockBajo(Model model) {
        List<InventarioArticulo> inventarioArticulosConStockBajo = inventarioArticuloService.obtenerInventarioArticulosConStockBajo();
        model.addAttribute("inventarioArticulos", inventarioArticulosConStockBajo);
        return "InventarioArticuloMenorStock";
    }
    @GetMapping("/inventarios/debajoPuntoPedido")
    public String mostrarInventarioArticulosDebajoPuntoPedido(Model model) {
        List<InventarioArticulo> inventarioArticulosDebajoPuntoPedido = inventarioArticuloService.obtenerInventarioArticulosDebajoPuntoPedido();
        model.addAttribute("inventarioArticulos", inventarioArticulosDebajoPuntoPedido);
        return "InventarioArticuloDebajoPuntoPedido";
    }

}
