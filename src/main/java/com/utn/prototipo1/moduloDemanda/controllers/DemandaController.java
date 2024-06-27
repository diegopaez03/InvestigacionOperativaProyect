package com.utn.prototipo1.moduloDemanda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.repositories.DemandaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("demanda")
public class DemandaController {

    @Autowired
    public DemandaRepository demandaRepository;

    @Autowired
    public ArticuloService articuloService;

    //Vista para generar la demanda
    @GetMapping("generar")
    public String getMethodName(Model model) {
        model.addAttribute("demanda", new Demanda());
        model.addAttribute("articulo", articuloService.getAllArticulos());
        return "moduloDemanda/registroDemanda";
    }
    

}
