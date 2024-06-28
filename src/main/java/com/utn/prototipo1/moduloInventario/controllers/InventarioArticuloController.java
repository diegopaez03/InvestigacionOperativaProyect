package com.utn.prototipo1.moduloInventario.controllers;

import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
import com.utn.prototipo1.moduloInventario.services.InventarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "inventarioArticulo")

public class InventarioArticuloController {
    @Autowired
    private InventarioServices inventarioServices;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private InventarioArticuloService inventarioArticuloService;



}

