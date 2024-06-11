package com.utn.prototipo1.moduloArticulo.controllers;

import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloArticulo.services.TipoModeloInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TipoModeloInventarioController {

    @Autowired
    private TipoModeloInventarioService tipoModeloInventarioService;

    @GetMapping("/tiposModelosInventario")
    public String listarTiposModelosInventario(Model modelo) {
        modelo.addAttribute("tiposModelosInventario", tipoModeloInventarioService.getAllTiposModelosInventario());
        return "tiposModelosInventario";
    }

    @GetMapping("/tiposModelosInventario/nuevo")
    public String mostrarFormularioCrearTipoModeloInventario(Model modelo) {
        TipoModeloInventario tipoModeloInventario = new TipoModeloInventario();
        modelo.addAttribute("tipoModeloInventario", tipoModeloInventario);
        return "registroTipoModeloInventario";
    }

    @PostMapping("/tiposModelosInventario")
    public String guardarTipoModeloInventario(@ModelAttribute("tipoModeloInventario") TipoModeloInventario tipoModeloInventario) {
        tipoModeloInventarioService.saveTipoModeloInventario(tipoModeloInventario);
        return "redirect:/tiposModelosInventario";
    }

    @GetMapping("/tiposModelosInventario/{id}")
    public String eliminarTipoModeloInventario(@PathVariable Long id) {
        tipoModeloInventarioService.deleteTipoModeloInventario(id);
        return "redirect:/tiposModelosInventario";
    }
}

