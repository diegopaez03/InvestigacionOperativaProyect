package com.utn.prototipo1.moduloOrdenCompra.controllers.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.utn.prototipo1.moduloInventario.services.InventarioArticuloServiceImpl;
import com.utn.prototipo1.moduloOrdenCompra.services.OrdenDeCompraService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("restOrdenDeCompra")
@CrossOrigin(origins = "*")
public class RestOrdenDeCompraController {

    @Autowired
    OrdenDeCompraService ordenDeCompraService;

    @Autowired
    private InventarioArticuloServiceImpl inventarioArticuloServiceImpl;

    @DeleteMapping("/{id}")
    public String deleteOrdenDeCompra(@PathVariable("id") Long id) throws Exception{
        ordenDeCompraService.delete(id);
        return ("Objeto eliminado, id: " + id);
    }

    @GetMapping("/loteOptimo/{idArticulo}")
    public double getLoteOptimo(@PathVariable("idArticulo") Long idArticulo) {
        double loteOptimo = inventarioArticuloServiceImpl.getLoteOptimoByArticulo(idArticulo);
        return loteOptimo;
    }

}
