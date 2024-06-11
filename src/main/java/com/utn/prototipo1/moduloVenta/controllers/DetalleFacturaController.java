package com.utn.prototipo1.moduloVenta.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.services.DetalleFacturaService;
import com.utn.prototipo1.moduloVenta.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private FacturaService facturaService;




}
