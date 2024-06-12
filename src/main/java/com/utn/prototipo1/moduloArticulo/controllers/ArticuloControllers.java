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
        return "articulos";
    }

    @GetMapping("/articulos/nuevo")
    public String mostrarFormularioCrearArticulo(Model modelo) {
        Articulo articulo = new Articulo();
        modelo.addAttribute("articulo", articulo);
        modelo.addAttribute("categorias", articuloCategoriaService.getAllCategorias());
        return "registroArticulo";
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


}


