package com.utn.prototipo1.moduloOrdenCompra.controllers.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.utn.prototipo1.moduloOrdenCompra.services.OrdenDeCompraService;


@RestController
@RequestMapping("restOrdenDeCompra")
@CrossOrigin(origins = "*")
public class RestOrdenDeCompraController {

    @Autowired
    OrdenDeCompraService ordenDeCompraService;

    @DeleteMapping("/{id}")
    public String deleteOrdenDeCompra(@PathVariable("id") Long id) throws Exception{
        ordenDeCompraService.delete(id);
        return ("Objeto eliminado, id: " + id);
    }

}
