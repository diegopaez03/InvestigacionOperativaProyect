package com.utn.prototipo1.moduloArticulo.controllers;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloArticulo.services.ArticuloCategoriaService;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloArticulo.services.TipoModeloInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class ArticuloCategoriaController {

    @Autowired
    private ArticuloCategoriaService articuloCategoriaService;

    @Autowired
    private TipoModeloInventarioService tipoModeloInventarioService;

    @GetMapping("/categorias")
    public String listarCategorias(Model modelo) {
        modelo.addAttribute("categorias", articuloCategoriaService.getAllCategorias());
        return "categorias";
    }

    @GetMapping("/categorias/nuevo")
    public String mostrarFormularioCrearCategoria(Model modelo) {
        ArticuloCategoria articuloCategoria = new ArticuloCategoria();
        modelo.addAttribute("categoria", articuloCategoria);
        modelo.addAttribute("tiposModeloInventario", tipoModeloInventarioService.getAllTiposModelosInventario());
        return "registroCategoria";
    }

    @PostMapping("/categorias")
    public String guardarCategoria(@ModelAttribute("categoria") ArticuloCategoria articuloCategoria) {
        articuloCategoriaService.saveCategoria(articuloCategoria);
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        articuloCategoriaService.deleteCategoria(id);
        return "redirect:/categorias";
    }


}
