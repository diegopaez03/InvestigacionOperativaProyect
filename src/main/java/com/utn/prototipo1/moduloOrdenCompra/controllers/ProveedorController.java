package com.utn.prototipo1.moduloOrdenCompra.controllers;

import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("proveedor")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping()
    public List<Proveedor> getAllProveedors(){
        return proveedorService.getProveedor();
    }

    @PostMapping()
    public  Proveedor createProveedor(@RequestBody Proveedor proveedor){
        return  proveedorService.saveProveedor(proveedor);
    }

    @GetMapping("/{id}")
    public Proveedor getProveedor(@PathVariable Long id){
        return proveedorService.getProveedorById(id);
    }

    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorRecibido){
        Proveedor proveedor = proveedorService.getProveedorById(id);

        proveedor.setNombreProveedor(proveedorRecibido.getNombreProveedor());
        proveedor.setFechaBajaProveedor(proveedorRecibido.getFechaBajaProveedor());

        return proveedorService.saveProveedor(proveedor);
    }

    @DeleteMapping("/{id}")
    public String deleteProveedor(@PathVariable Long id){
        var proveedor = proveedorService.getProveedorById(id);
        proveedorService.deleteProveedor(proveedor);
        return ("Objeto eliminado, id: " + id);
    }
}
