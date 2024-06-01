package com.utn.prototipo1.moduloArticulo.controllers;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloArticulo.services.ArticuloCategoriaService;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/articuloCategoria")
public class ArticuloCategoriaController {

    @Autowired
    private ArticuloCategoriaService articuloCategoriaService;

    @GetMapping()
    public List<ArticuloCategoria> getAllArticuloCategoria(){
        return articuloCategoriaService.getArticuloCategoria();
    }

    @PostMapping()
    public  ArticuloCategoria createArticuloCategoria(@RequestBody ArticuloCategoria articuloCategoria){
        return  articuloCategoriaService.saveArticuloCategoria(articuloCategoria);
    }

    @GetMapping("/{id}")
    public ArticuloCategoria getArticuloCategoria(@PathVariable Long id){
        return articuloCategoriaService.getArticuloCategoriaById(id);
    }

    @PutMapping("/{id}")
    public ArticuloCategoria updateArticuloCategoria(@PathVariable Long id, @RequestBody ArticuloCategoria articuloCategoriaRecibido){
        ArticuloCategoria articuloCategoria = articuloCategoriaService.getArticuloCategoriaById(id);

        articuloCategoria.setNombreCategoria(articuloCategoriaRecibido.getNombreCategoria());
        articuloCategoria.setFechaBaja(articuloCategoriaRecibido.getFechaBaja());

        return articuloCategoriaService.saveArticuloCategoria(articuloCategoria);
    }

    @DeleteMapping("/{id}")
    public String deleteArticuloCategoria(@PathVariable Long id){
        var articuloCategoria = articuloCategoriaService.getArticuloCategoriaById(id);
        articuloCategoriaService.deleteArticuloCategoria(articuloCategoria);
        return ("Objeto eliminado, id: " + id);
    }

}
