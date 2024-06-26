package com.utn.prototipo1.moduloOrdenCompra.controllers;

import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.services.EstadoOrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estadoOrdenCompra")
@CrossOrigin(origins = "*")
public class EstadoOrdenCompraController {

    @Autowired
    private EstadoOrdenCompraService estadoOrdenCompraService;

    @GetMapping()
    public List<EstadoOrdenDeCompra> getAllEstadoOrdenDeCompras(){
        return estadoOrdenCompraService.getEstadoOrdenCompra();
    }

    @PostMapping()
    public  EstadoOrdenDeCompra createEstadoOrdenDeCompra(@RequestBody EstadoOrdenDeCompra estadoOrdenDeCompra){
        return  estadoOrdenCompraService.saveEstadoOrdenDeCompra(estadoOrdenDeCompra);
    }

    @GetMapping("/{id}")
    public EstadoOrdenDeCompra getEstadoOrdenDeCompra(@PathVariable("id") Long id){
            return estadoOrdenCompraService.getEstadoOrdenDeCompraById(id);
        }

    @PutMapping("/{id}")
    public EstadoOrdenDeCompra updateEstadoOrdenDeCompra(@PathVariable("id") Long id, @RequestBody EstadoOrdenDeCompra estadoOrdenDeCompraRecibido){
        EstadoOrdenDeCompra estadoOrdenDeCompra = estadoOrdenCompraService.getEstadoOrdenDeCompraById(id);

        estadoOrdenDeCompra.setFechaBaja(estadoOrdenDeCompraRecibido.getFechaBaja());
        estadoOrdenDeCompra.setNombreEOC(estadoOrdenDeCompraRecibido.getNombreEOC());

        return estadoOrdenCompraService.saveEstadoOrdenDeCompra(estadoOrdenDeCompra);
    }

    @DeleteMapping("/{id}")
    public String deleteEstadoOrdenDeCompra(@PathVariable("id") Long id){
        var eoc = estadoOrdenCompraService.getEstadoOrdenDeCompraById(id);
        estadoOrdenCompraService.deleteEstadoOrdenCompra(eoc);
        return ("Objeto eliminado, id: " + id);
    }
}
