package com.utn.prototipo1.moduloInventario.controllers;

import com.utn.prototipo1.Base.controllers.BaseControllerImpl;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
import com.utn.prototipo1.moduloInventario.services.InventarioServices;
import com.utn.prototipo1.moduloInventario.services.InventarioServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller

public class InventarioController  {

        @Autowired
        private InventarioServices inventarioServices;

        @Autowired
        private  ArticuloService articuloService;

        @Autowired
        private InventarioArticuloService inventarioArticuloService;

        //Listar los inventarios que se crean
        @GetMapping("/maestroinventario")
        public String mostrarTodosLosInventarios(Model model) {
            model.addAttribute("inventarios", inventarioServices.obtenerTodosLosInventario());
            return "MaestroInventario";
        }

        @GetMapping("/maestroinventario/nuevo")
        public String mostrarFormularioCrearInventario(Model model) {
            Inventario inventario = new Inventario();
            inventario.getInventarioArticulos().add(new InventarioArticulo()); // Agrega un detalle de factura por defecto
            model.addAttribute("inventario", inventario);
            model.addAttribute("articulos", articuloService.getAllArticulos());
            return "crear-inventario";
        }


        @PostMapping("/inventarios")
        public String crearInventario(@ModelAttribute("inventario") Inventario inventario) {
            inventario.setFechaDesde(new Date()); // Asignar la fecha y hora actual
            for (InventarioArticulo inventarioArticulo : inventario.getInventarioArticulos()) {
                inventarioArticulo.setInventario(inventario);// Establece la relación entre la factura y sus detalles
            }
            inventarioServices.crearInventario(inventario);
            return "redirect:/maestroinventario";
        }

        //borrar el inventario
        @GetMapping("/maestroinventario/{id}")
        public String eliminarInventario(@PathVariable Long id){
            inventarioServices.deleteInventario(id);
            return "redirect:/maestroinventario";
        }


        //DETALLE FACTURA ------------------------------

        @GetMapping("/inventarios/{inventarioId}/inventarioArticulos/nuevo")
        public String mostrarFormularioCrearInventarioArticulo(@PathVariable("inventarioId") Long InventarioId, Model model) {
            Inventario inventario = inventarioServices.obtenerInventarioPorId(InventarioId);
            List<Articulo> articulos = articuloService.getAllArticulos();
            InventarioArticulo inventarioArticulo = new InventarioArticulo();
            inventarioArticulo.setInventario(inventario);
            model.addAttribute("inventarioArticulo", inventarioArticulo);
            model.addAttribute("articulos", articulos);
            model.addAttribute("inventario", inventario);
            return "crear-inventario-articulo";
        }

        @PostMapping("/inventario/{inventarioId}/inventarioArticulos")
        public String crearinventarioArticulo(@PathVariable("inventarioId") Long InventarioId,
                                          @ModelAttribute("inventarioArticulo") InventarioArticulo inventarioArticulo,
                                          @RequestParam("articulo.id") Long articuloId) {
            Inventario inventario = inventarioServices.obtenerInventarioPorId(InventarioId);
            Articulo articulo = articuloService.getArticuloById(articuloId);
            inventarioArticulo.setInventario(inventario);
            inventarioArticulo.setArticulo(articulo);
            inventarioArticuloService.save(inventarioArticulo);
            return "redirect:/maestroinventario";
        }


        @GetMapping("/inventarios/{inventarioId}/inventarioArticulos")
        public String verInventarioArticulo(@PathVariable Long InventarioId, Model model) {
            List<InventarioArticulo> inventarioArticulos = inventarioArticuloService.obtenerInventarioArticulos(InventarioId);
            model.addAttribute("inventarioArticulo", inventarioArticulos);
            return "MaestroInventarioArticulo";
        }

    }
