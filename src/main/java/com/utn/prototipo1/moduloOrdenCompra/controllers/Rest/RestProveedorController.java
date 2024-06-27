package com.utn.prototipo1.moduloOrdenCompra.controllers.Rest;

import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("restProveedor")
@CrossOrigin(origins = "*")
public class RestProveedorController {

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

    @GetMapping("/byArticulo/{idArticulo}")
    public List<Proveedor> getProveedoresConArticuloById(@PathVariable("idArticulo") Long idArticulo) {
        return proveedorService.getProveedoresConArticulo(idArticulo);
    }

    @GetMapping("/{idArticulo}/menorDemora")
    public ProveedorArticulo getProveedorArticuloConMenorDemoraById(@PathVariable("idArticulo") Long idArticulo) {
        return proveedorService.getProveedorArticuloConMenorDemora(idArticulo);
    }
    

    @GetMapping("/{id}")
    public Proveedor getProveedor(@PathVariable("id") Long id){
        return proveedorService.getProveedorById(id);
    }

    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable("id") Long id, @RequestBody Proveedor proveedorRecibido){
        Proveedor proveedor = proveedorService.getProveedorById(id);

        proveedor.setNombreProveedor(proveedorRecibido.getNombreProveedor());
        proveedor.setFechaBajaProveedor(proveedorRecibido.getFechaBajaProveedor());

        return proveedorService.saveProveedor(proveedor);
    }

    @DeleteMapping("/{id}")
    public String deleteProveedor(@PathVariable("id") Long id){
        var proveedor = proveedorService.getProveedorById(id);
        proveedorService.deleteProveedor(proveedor);
        return ("Objeto eliminado, id: " + id);
    }
}
