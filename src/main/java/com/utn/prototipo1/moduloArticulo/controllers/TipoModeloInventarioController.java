package com.utn.prototipo1.moduloArticulo.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
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
        return "moduloArticulos/tiposModelosInventario";
    }

    @GetMapping("/tiposModelosInventario/nuevo")
    public String mostrarFormularioCrearTipoModeloInventario(Model modelo) {
        TipoModeloInventario tipoModeloInventario = new TipoModeloInventario();
        modelo.addAttribute("tipoModeloInventario", tipoModeloInventario);
        return "moduloArticulos/registroTipoModeloInventario";
    }

    @PostMapping("/tiposModelosInventario")
    public String guardarTipoModeloInventario(@ModelAttribute("tipoModeloInventario") TipoModeloInventario tipoModeloInventario) {
        tipoModeloInventarioService.saveTipoModeloInventario(tipoModeloInventario);
        return "redirect:/tiposModelosInventario";
    }

    @GetMapping("/tiposModelosInventario/{id}")
    public String eliminarTipoModeloInventario(@PathVariable("id") Long id) {
        tipoModeloInventarioService.deleteTipoModeloInventario(id);
        return "redirect:/tiposModelosInventario";
    }

    @GetMapping("/tiposModelosInventario/editar/{id}")
    public String mostrarFormularioEditarTMI(@PathVariable Long id, Model modelo){
        modelo.addAttribute("tipoModeloInventario",tipoModeloInventarioService.getTipoModeloInventarioById(id));
        return "moduloArticulos/editarTipoModeloInventario";
    }

    @PostMapping("tiposModelosInventario/{id}")
    public String editarTMI(@PathVariable Long id, @ModelAttribute("tipoModeloInventario") TipoModeloInventario tipoModeloInventario, Model modelo){
        TipoModeloInventario tipoModeloInventario1 = tipoModeloInventarioService.getTipoModeloInventarioById(id);
        tipoModeloInventario1.setId(id);
        tipoModeloInventario1.setNombre(tipoModeloInventario.getNombre());
        tipoModeloInventario1.setFechaBajaTMI(tipoModeloInventario.getFechaBajaTMI());

        tipoModeloInventarioService.saveTipoModeloInventario(tipoModeloInventario1);
        return "redirect:/tiposModelosInventario";
    }
}

