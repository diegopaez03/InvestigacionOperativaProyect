package com.utn.prototipo1.moduloOrdenCompra.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloOrdenCompra.dto.RegistrarProveedorDTO;
import com.utn.prototipo1.moduloOrdenCompra.entities.DetalleOrdenCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.OrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;






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
    public ModelAndView registrarProveedor(@ModelAttribute("proveedor") RegistrarProveedorDTO registrarProveedorDTO) {
        try {
            proveedorService.registrarProveedor(registrarProveedorDTO);
            return new ModelAndView("redirect:/proveedor/list");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Lista de proveedores
    @GetMapping("/list")
    public String vistaListaProveedores(Model model) {
        List<Proveedor> proveedores = proveedorService.getProveedor();
        model.addAttribute("proveedores", proveedores);

        return "moduloOrdenCompra/proveedor/menuProveedor";
    }

    //Ver proveedor
    @GetMapping("/{id}")
    public String verProveedor(@PathVariable("id") Long id, Model model) throws Exception {
        Proveedor proveedor = proveedorService.getProveedorById(id);

        if (proveedor != null) {
            model.addAttribute("proveedor", proveedor);
            return "moduloOrdenCompra/proveedor/verProveedor";
        } else {
            return "redirect:/proveedor/list";
        }
    }

    //Vista de eliminar el proveedor
    @GetMapping("/eliminar/{idProveedor}")
    public String vistaEliminarProveedor(@PathVariable("idProveedor") Long idProveedor, Model model) {
        Proveedor proveedor = proveedorService.getProveedorById(idProveedor);
        model.addAttribute("proveedor", proveedor);

        return "moduloOrdenCompra/proveedor/eliminarProveedor";
    }

    //Funcion eliminar proveedor
    @GetMapping("eliminar/proveedor/{idProveedor}")
    public String eliminarProveedor(@PathVariable("idProveedor") Long idProveedor) {
        Proveedor proveedor = proveedorService.getProveedorById(idProveedor);
        proveedorService.deleteProveedor(proveedor);
        return "redirect:/proveedor/list";
    }

    //Vista de agregar articulos a proveedor
    @GetMapping("/agregarArticulo/{id}")
    public String vistaAgregarArticulo(@PathVariable("id") Long id, Model model) throws Exception {
        Proveedor proveedor = proveedorService.getProveedorById(id);

        List<Articulo> allArticulos = articuloRepository.findAll();

        List<Articulo> articulos = allArticulos.stream()
        .filter(articulo -> proveedor.getProveedorArticulo().stream()
            .noneMatch(proveedorArticulo -> proveedorArticulo.getArticulo().equals(articulo)))
        .distinct()
        .collect(Collectors.toList());

        model.addAttribute("proveedor", proveedor);
        model.addAttribute("articulos", articulos);

        return "moduloOrdenCompra/proveedor/editarProveedor";
    }
    
    //Funcion para agregar articulo
    @PostMapping("/{id}/agregarArticulo")
    public String agregarArticulo(@PathVariable("id") Long id,
                                @RequestParam("idArticulo") Long idArticulo,
                                @RequestParam("costoPedido") double costoPedido,
                                @RequestParam("tiempoDemora") Integer tiempoDemora,
                                Model model) throws Exception {

        Proveedor proveedorExistente = proveedorService.getProveedorById(id);
        Articulo articulo = articuloRepository.findById(idArticulo)
                            .orElseThrow(() -> new Exception("Artículo no encontrado"));

        ProveedorArticulo proveedorArticulo = new ProveedorArticulo();
        proveedorArticulo.setArticulo(articulo);
        proveedorArticulo.setCostoPedido(costoPedido);
        proveedorArticulo.setTiempoDemoraArticulo(tiempoDemora);

        proveedorExistente.getProveedorArticulo().add(proveedorArticulo);
        proveedorService.saveProveedor(proveedorExistente);

        return "redirect:/proveedor/list";
    }



}
