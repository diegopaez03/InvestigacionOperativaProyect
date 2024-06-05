package com.utn.prototipo1.moduloOrdenCompra.controllers;

import com.utn.prototipo1.moduloOrdenCompra.services.DetalleOrdenCompraService;
import com.utn.prototipo1.moduloOrdenCompra.services.OrdenDeCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ordenDeCompra")
@CrossOrigin(origins = "*")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraService ordenDeCompraService;

    @Autowired
    private DetalleOrdenCompraService detalleOrdenCompraService;

}
