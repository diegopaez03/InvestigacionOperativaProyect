package com.utn.prototipo1.moduloOrdenCompra.controllers.Rest;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloOrdenCompra.dto.RegistrarProveedorDTO;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorArticuloService;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("restProveedor")
@CrossOrigin(origins = "*")
public class RestProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorArticuloService proveedorArticuloService;

    @GetMapping()
    public List<Proveedor> getAllProveedors(){
        return proveedorService.getProveedor();
    }

    @PostMapping()
    public  Proveedor createProveedor(@RequestBody Proveedor proveedor){
        return  proveedorService.saveProveedor(proveedor);
    }

    @PostMapping("/proveedorArticulo")
    public ProveedorArticulo createProveedorArticulo(@RequestBody Long idArticulo, double costoPedido, int tiempoDemora) throws Exception {

        return proveedorArticuloService.generarProveedorArticulo(idArticulo, costoPedido, tiempoDemora);
    }

    @PostMapping("/registrar")
    public  Proveedor registraProveedor(@RequestBody RegistrarProveedorDTO registrarProveedorDTO) throws Exception{
        return  proveedorService.registrarProveedor(registrarProveedorDTO);
    }


    @GetMapping("/{idProveedor}/articulos")
    public List<Articulo> getArticulosDeProveedor(@PathVariable("idProveedor") Long idProveedor) {
        Proveedor proveedor = proveedorService.getProveedorById(idProveedor);

        return proveedorService.getArticulosOfProveedor(proveedor);
        
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
