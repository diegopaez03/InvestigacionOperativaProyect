package com.utn.prototipo1.moduloOrdenCompra.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloOrdenCompra.dto.RegistrarProveedorDTO;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



@Controller
@RequestMapping("proveedor")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ArticuloRepository articuloRepository;

    //Vista registrar Proveedor
    @GetMapping("/registrar")
    public String vistaRegistrarProveedor(Model model) {
        List<Articulo> articulos = articuloRepository.findAll();
        model.addAttribute("articulos", articulos);
        model.addAttribute("proveedor", new RegistrarProveedorDTO());
        return "moduloOrdenCompra/proveedor/registroProveedor";
    }

    //Función registrar Proveedor
    @PostMapping("/registrar")
    public String registrarProveedor(@ModelAttribute("proveedor") RegistrarProveedorDTO registrarProveedorDTO) {
        System.out.println(registrarProveedorDTO);
        try {
            proveedorService.registrarProveedor(registrarProveedorDTO);
            return "redirect:/ordenDeCompra/list"; 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
    

}
