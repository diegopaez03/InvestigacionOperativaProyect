package com.utn.prototipo1.moduloArticulo.controllers;

import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloArticulo.services.TipoModeloInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/tipomodeloinventario")

public class TipoModeloInventarioController {
    @Autowired
    private TipoModeloInventarioService tipoModeloInventarioService;

    @GetMapping()
    public List<TipoModeloInventario> getAllTipoModeloInventarios(){
        return tipoModeloInventarioService.getTipoModeloInventario();
    }

    @PostMapping()
    public TipoModeloInventario createTipoModeloInventario(@RequestBody TipoModeloInventario tipoModeloInventario){
        return tipoModeloInventarioService.saveTipoModeloInventario(tipoModeloInventario);
    }

    @GetMapping("/{id}")
    public TipoModeloInventario getTipoModeloInventario(@PathVariable Long id){
        return tipoModeloInventarioService.getTipoModeloInventarioById(id);
    }

    @PutMapping("/{id}")
    public TipoModeloInventario updateTipoModeloInventario(@PathVariable Long id, @RequestBody TipoModeloInventario tipoModeloInventarioRecibido){
        TipoModeloInventario tipoModeloInventario = tipoModeloInventarioService.getTipoModeloInventarioById(id);

        tipoModeloInventario.setNombreTMI(tipoModeloInventarioRecibido.getNombreTMI());
        tipoModeloInventario.setFechaBajaTMI(tipoModeloInventarioRecibido.getFechaBajaTMI());

        return tipoModeloInventarioService.saveTipoModeloInventario(tipoModeloInventario);
    }

    @DeleteMapping("/{id}")
    public String deleteTipoModeloInventario(@PathVariable Long id){
        var tipoModeloInventario = tipoModeloInventarioService.getTipoModeloInventarioById(id);
        tipoModeloInventarioService.deleteTipoModeloInventario(tipoModeloInventario);
        return ("Objeto eliminado, id: " + id);
    }


}
