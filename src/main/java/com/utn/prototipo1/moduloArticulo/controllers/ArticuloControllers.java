package com.utn.prototipo1.moduloArticulo.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "articulos")

public class ArticuloControllers {

    @Autowired
    private ArticuloService articuloService;


    @GetMapping()
    public List<Articulo> getAllArticulos(){
        return articuloService.getAllArticulos();
    }

    @PostMapping()
    public  Articulo createArticulo(@RequestBody Articulo articulo){
        return  articuloService.saveArticulo(articulo);
    }

    @GetMapping("/{id}")
    public Articulo getArticulo(@PathVariable Long id){
        return articuloService.getArticuloById(id);
    }

    @PutMapping("/{id}")
    public Articulo updateArticulo(@PathVariable Long id, @RequestBody Articulo articuloRecibido){
        Articulo articulo = articuloService.getArticuloById(id);

        articulo.setNombreArticulo(articuloRecibido.getNombreArticulo());
        articulo.setFechaBaja(articuloRecibido.getFechaBaja());
        articulo.setPrecio(articuloRecibido.getPrecio());

        return articuloService.saveArticulo(articulo);
    }

    @DeleteMapping("/{id}")
    public String deleteArticulo(@PathVariable Long id){
        var articulo = articuloService.getArticuloById(id);
        articuloService.deleteArticulo(articulo.getId());
        return ("Objeto eliminado, id: " + id);
    }
}


