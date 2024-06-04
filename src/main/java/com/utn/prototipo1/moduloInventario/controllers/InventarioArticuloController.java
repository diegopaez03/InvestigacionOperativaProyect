package com.utn.prototipo1.moduloInventario.controllers;

import com.utn.prototipo1.Base.controllers.BaseControllerImpl;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "inventarioarticulo")
public class InventarioArticuloController extends BaseControllerImpl<InventarioArticulo, InventarioArticuloServiceImpl> {
}
