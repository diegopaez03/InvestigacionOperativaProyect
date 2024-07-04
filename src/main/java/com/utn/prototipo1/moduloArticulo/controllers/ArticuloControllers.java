package com.utn.prototipo1.moduloArticulo.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloCategoriaService;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.services.DemandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller

public class ArticuloControllers {

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ArticuloCategoriaService articuloCategoriaService;

    @Autowired
    private DemandaService demandaService;


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

    @GetMapping("/articulos/calculardemanda/{idArticulo}")
    public String mostrarFormularioCalcularDemanda(@PathVariable("idArticulo") Long idArticulo, Model modelo) {
        List<Demanda> demandas = demandaService.getDemandasByArticulo(idArticulo);
        
        // Ordenar demandas por periodoYear de más reciente a menos reciente
        demandas.sort(Comparator.comparing(Demanda::getPeriodoYear));
        
        // Extraer las cantidades de demanda y convertir a String sin corchetes
        String cantidadesDemanda = demandas.stream()
                                        .map(demanda -> String.valueOf(demanda.getCantidad()))
                                        .collect(Collectors.joining(", "));
        
        modelo.addAttribute("cantidadesDemanda", cantidadesDemanda);
        // Lógica adicional si es necesaria antes de mostrar el formulario
        return "formulario"; // Esto debería apuntar a "formulario.html" en templates/
    }

}


