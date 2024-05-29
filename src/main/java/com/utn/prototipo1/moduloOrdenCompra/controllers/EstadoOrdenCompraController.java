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
    public EstadoOrdenDeCompra getEstadoOrdenDeCompra(@PathVariable Long id){
            return estadoOrdenCompraService.getEstadoOrdenDeCompraById(id);
        }

    @PutMapping("/{id}")
    public EstadoOrdenDeCompra updateEstadoOrdenDeCompra(@PathVariable Long id, @RequestBody EstadoOrdenDeCompra estadoOrdenDeCompraRecibido){
        EstadoOrdenDeCompra estadoOrdenDeCompra = estadoOrdenCompraService.getEstadoOrdenDeCompraById(id);

        estadoOrdenDeCompra.setCodEOC(estadoOrdenDeCompraRecibido.getCodEOC());
        estadoOrdenDeCompra.setFechaBaja(estadoOrdenDeCompraRecibido.getFechaBaja());
        estadoOrdenDeCompra.setNombreEOC(estadoOrdenDeCompraRecibido.getNombreEOC());

        return estadoOrdenCompraService.saveEstadoOrdenDeCompra(estadoOrdenDeCompra);
    }
}
