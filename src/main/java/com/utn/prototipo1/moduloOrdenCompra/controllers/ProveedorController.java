package com.utn.prototipo1.moduloOrdenCompra.controllers;

import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("proveedor")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("registrar")
    public String registrarProveedor(Model model) {
        
        return new String();
    }
    

}
