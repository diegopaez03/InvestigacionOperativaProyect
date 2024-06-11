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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

public class InventarioController  {

        @Autowired
        private InventarioServices inventarioServices;

        @Autowired
        private  ArticuloService articuloService;

        @Autowired
        private InventarioArticuloService inventarioArticuloService;

        //Listar los inventarios que se crean
        @GetMapping("/maestoInventario")
        public String mostrarTodosloInventarios(Model model) {
            model.addAttribute("inventario", inventarioServices.obtenerTodosLosInventarios());
            return "MaestroInventario";
        }

        @GetMapping("/maestoInventario/nuevo")
        public String mostrarFormularioCrearInventario(Model model) {
            Inventario inventario = new Inventario();
            inventario.getInventarioArticulos().add(new InventarioArticulo()); // Agrega un detalle de factura por defecto
            model.addAttribute("inventario", inventario);
            model.addAttribute("articulos", articuloService.getArticulo());
            return "crear-inventario";
        }


        @PostMapping("/inventario")
        public String crearInventario(@ModelAttribute("invetario") Inventario inventario) {
            inventario.setFechaDesde(new Date()); // Asignar la fecha y hora actual
            for (InventarioArticulo inventarioArticulo : inventario.getInventarioArticulos()) {
                inventarioArticulo.setInventario(inventario); // Establece la relación entre la factura y sus detalles
            }
            inventarioServices.crearInventario(inventario);
            return "redirect:/maestoInventario";
        }

        //borrar el inventario
        @GetMapping("/maestoInventario/{id}")
        public String eliminarInventario(@PathVariable Long id){
            inventarioServices.deleteInventario(id);
            return "redirect:/maestoInventario";
        }


        //DETALLE FACTURA ------------------------------

        @GetMapping("/inventario/{InventarioId}/inventarioArticulo/nuevo")
        public String mostrarFormularioCrearInventarioArticulo(@PathVariable("InventarioId") Long InventarioId, Model model) {
            Inventario inventario = inventarioServices.obtenerInventarioId(InventarioId);
            List<Articulo> articulos = articuloService.getArticulo();
            InventarioArticulo inventarioArticulo = new InventarioArticulo();
            inventarioArticulo.setInventario(inventario);
            model.addAttribute("inventarioArticulo", inventarioArticulo);
            model.addAttribute("articulos", articulos);
            model.addAttribute("inventario", inventario);
            return "crear-inventario-articulo";
        }

        @PostMapping("/inventario/{InventarioId}/inventarioArticulo")
        public String crearinventarioArticulo(@PathVariable("InventarioId") Long InventarioId,
                                          @ModelAttribute("inventarioArticulo") InventarioArticulo inventarioArticulo,
                                          @RequestParam("articulo.id") Long articuloId) {
            Inventario inventario = inventarioServices.obtenerInventarioId(InventarioId);
            Articulo articulo = articuloService.getArticuloById(articuloId);
            inventarioArticulo.setInventario(inventario);
            inventarioArticulo.setArticulo(articulo);
            //inventarioArticulo.calcularLinea(); // Llama al método que calcula el valor de la línea
            inventarioArticuloService.save(inventarioArticulo);
            inventarioServices.actualizarInventario(InventarioId);
            return "redirect:/maestoInventario" ;
        }


        @GetMapping("/inventario/{InventarioId}/inventarioArticulo")
        public String verInventarioArticulo(@PathVariable Long InventarioId, Model model) {
            List<InventarioArticulo> inventarioArticulos = inventarioArticuloService.obtenerInventarioArticulos(InventarioId);
            model.addAttribute("inventarioArticulo", inventarioArticulos);
            return "MaestroDetalleInventario";
        }

    }
