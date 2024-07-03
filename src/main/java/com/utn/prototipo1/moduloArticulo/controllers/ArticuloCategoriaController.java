package com.utn.prototipo1.moduloArticulo.controllers;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloArticulo.services.ArticuloCategoriaService;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloArticulo.services.TipoModeloInventarioService;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
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
    private ArticuloRepository articuloRepository;
    @Autowired
    private TipoModeloInventarioService tipoModeloInventarioService;
    @Autowired
    private InventarioArticuloRepository inventarioArticuloRepository;
    @Autowired
    private InventarioArticuloService inventarioArticuloService;


    @GetMapping("/categorias")
    public String listarCategorias(Model modelo) {
        modelo.addAttribute("categorias", articuloCategoriaService.getAllCategorias());
        return "moduloArticulos/categorias";
    }

    @GetMapping("/categorias/nuevo")
    public String mostrarFormularioCrearCategoria(Model modelo) {
        ArticuloCategoria articuloCategoria = new ArticuloCategoria();
        modelo.addAttribute("categoria", articuloCategoria);
        List<TipoModeloInventario> tiposModelosInventario = tipoModeloInventarioService.getAllTiposModelosInventario();
        modelo.addAttribute("tiposModelosInventario", tiposModelosInventario);
        return "moduloArticulos/registroCategoria";
    }

    @PostMapping("/categorias")
    public String guardarCategoria(@ModelAttribute("categoria") ArticuloCategoria articuloCategoria) {
        articuloCategoriaService.saveCategoria(articuloCategoria);
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id) {
        articuloCategoriaService.deleteCategoria(id);
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/editar/{id}")
    public String mostrarFormularioEditarCategoria(@PathVariable Long id, Model modelo){
        modelo.addAttribute("categoria",articuloCategoriaService.getCategoriaById(id));
        List<TipoModeloInventario> tiposModelosInventario = tipoModeloInventarioService.getAllTiposModelosInventario();
        modelo.addAttribute("tiposModelosInventario", tiposModelosInventario);
        return "moduloArticulos/editarCategoria";
    }

    @PostMapping("/categorias/{id}")
    public String editarCategoria(@PathVariable Long id, @ModelAttribute("categoria") ArticuloCategoria articuloCategoria, Model modelo){
        ArticuloCategoria categoria1 = articuloCategoriaService.getCategoriaById(id);
        boolean tipoModeloInventarioCambiado = !categoria1.getTipoModeloInventario().equals(articuloCategoria.getTipoModeloInventario());

        categoria1.setId(id);
        categoria1.setNombreCategoria(articuloCategoria.getNombreCategoria());
        categoria1.setFechaBaja(articuloCategoria.getFechaBaja());
        categoria1.setTipoModeloInventario(articuloCategoria.getTipoModeloInventario());

        if (tipoModeloInventarioCambiado) {
            List<Articulo> articulos = articuloRepository.findByArticuloCategoria(categoria1);
            for (Articulo articulo : articulos) {
                InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findByArticulo(articulo);
                inventarioArticuloService.calcularVariables(inventarioArticulo.getId());
            }
        }

        articuloCategoriaService.saveCategoria(categoria1);
        return "redirect:/categorias";
    }


}
