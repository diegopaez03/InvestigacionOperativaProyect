package com.utn.prototipo1.moduloInventario.controllers;

import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
import com.utn.prototipo1.moduloInventario.services.InventarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "inventarioarticulo")

public class InventarioArticuloController {
    @Autowired
    private InventarioServices inventarioServices;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private InventarioArticuloService inventarioArticuloService;


    @GetMapping("/{id}/calcular-y-actualizar")
    public String calcularYActualizar(
            @RequestParam Long inventarioArticuloId,
            @RequestParam String tipoModeloInventario,
            @RequestParam Double parametro1,
            @RequestParam Double parametro2) {

        inventarioArticuloService.calcularVariables(inventarioArticuloId, tipoModeloInventario, parametro1, parametro2);

        return "MaestroInventarioArticulo";
    }
}
