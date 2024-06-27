package com.utn.prototipo1.moduloArticulo.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloCategoriaService;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class ArticuloControllers {

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ArticuloCategoriaService articuloCategoriaService;


    @GetMapping("/articulos")
    public String listarArticulos(Model modelo) {
        modelo.addAttribute("articulos", articuloService.getAllArticulos());
        return "moduloArticulos/articulos";
    }

    @GetMapping("/articulos/nuevo")
    public String mostrarFormularioCrearArticulo(Model modelo) {
        Articulo articulo = new Articulo();
        modelo.addAttribute("articulo", articulo);
        modelo.addAttribute("categorias", articuloCategoriaService.getAllCategorias());
        return "moduloArticulos/registroArticulo";
    }

    @PostMapping("/articulos")
    public String guardarArticulo(@ModelAttribute("articulo") Articulo articulo) {
        articuloService.saveArticulo(articulo);
        return "redirect:/articulos";
    }

    @GetMapping("/articulos/{id}")
    public String eliminarArticulo(@PathVariable("id") Long id) {
        articuloService.deleteArticulo(id);
        return "redirect:/articulos";
    }

    @GetMapping("/articulos/editar/{id}")
    public String mostrarFormularioEditarArticulo(@PathVariable Long id, Model modelo){
        modelo.addAttribute("articulo",articuloService.getArticuloById(id));
        modelo.addAttribute("categorias", articuloCategoriaService.getAllCategorias());
        return "moduloArticulos/editarArticulo";
    }

    @PostMapping("articulos/{id}")
    public String editarArticulo(@PathVariable Long id, @ModelAttribute("articulo") Articulo articulo, Model modelo){
        Articulo articulo1 = articuloService.getArticuloById(id);
        articulo1.setId(id);
        articulo1.setNombreArticulo(articulo.getNombreArticulo());
        articulo1.setFechaBaja(articulo.getFechaBaja());
        articulo1.setPrecioCompra(articulo.getPrecioCompra());
        articulo1.setPrecioVenta(articulo.getPrecioVenta());
        articulo1.setArticuloCategoria(articulo.getArticuloCategoria());

        articuloService.saveArticulo(articulo1);
        return "redirect:/articulos";
    }

    @GetMapping("/articulos/calculardemanda")
    public String mostrarFormularioCalcularDemanda(Model modelo) {
        // Lógica adicional si es necesaria antes de mostrar el formulario
        return "formulario"; // Esto debería apuntar a "formulario.html" en templates/
    }

}


