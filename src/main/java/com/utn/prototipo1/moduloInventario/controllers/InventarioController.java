package com.utn.prototipo1.moduloInventario.controllers;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
import com.utn.prototipo1.moduloInventario.services.InventarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class InventarioController {

    @Autowired
    private InventarioServices inventarioServices;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private InventarioArticuloService inventarioArticuloService;

    @GetMapping("/maestroinventario")
    public String mostrarTodosLosInventarios(Model model) {
        model.addAttribute("inventarios", inventarioServices.findAll());
        return "MaestroInventario";
    }

    @GetMapping("/maestroinventario/nuevo")
    public String mostrarFormularioCrearInventario(Model model) {
        Inventario inventario = new Inventario();
        inventario.getInventarioArticulos().add(new InventarioArticulo());
        model.addAttribute("inventario", inventario);
        model.addAttribute("articulos", articuloService.getAllArticulos());
        return "crear-inventario";
    }

    @PostMapping("/inventarios")
    public String crearInventario(@ModelAttribute("inventario") Inventario inventario) {
        inventario.setFechaDesde(LocalDate.now());
        for (InventarioArticulo inventarioArticulo : inventario.getInventarioArticulos()) {
            inventarioArticulo.setInventario(inventario);
            inventario.getInventarioArticulos().add(inventarioArticulo);
        }
        inventarioServices.crearInventario(inventario);
        return "redirect:/maestroinventario";
    }

    @GetMapping("/maestroinventario/{id}")
    public String eliminarInventario(@PathVariable("id") Long id) {
        inventarioServices.deleteById(id);
        return "redirect:/maestroinventario";
    }


    @PostMapping("/inventarios/{inventarioId}/inventarioArticulos")
    public String crearinventarioArticulo(@PathVariable("inventarioId") Long inventarioId,
                                          @ModelAttribute("inventarioArticulo") InventarioArticulo inventarioArticulo,
                                          @RequestParam("articulo.id") Long articuloId) {
        Inventario inventario = inventarioServices.obtenerInventarioPorId(inventarioId);
        Articulo articulo = articuloService.getArticuloById(articuloId);
        inventarioArticulo.setInventario(inventario);
        inventarioArticulo.setArticulo(articulo);
        inventario.getInventarioArticulos().add(inventarioArticulo);
        inventarioArticuloService.save(inventarioArticulo);
        return "redirect:/maestroinventario";
    }

    @GetMapping("/inventarios/{inventarioId}/inventarioArticulos")
    public String verInventarioArticulo(@PathVariable("inventarioId") Long inventarioId, Model model) {
        List<InventarioArticulo> inventarioArticulos = inventarioArticuloService.obtenerArticuloPorInventario(inventarioId);
        model.addAttribute("inventarioArticulos", inventarioArticulos);
        return "MaestroInventarioArticulo";
    }

    @GetMapping("/inventarios/{inventarioId}/inventarioArticulos/calcularVariablesForm/{id}")
    public String showCalcularVariablesForm(@PathVariable("inventarioId") Long inventarioId,
                                            @PathVariable("id") Long id, Model model) {
        model.addAttribute("inventarioArticuloId", id);
        return "calcularVariableForm";
    }

    @PostMapping("/calcularVariables")
    public String calcularVariables(@RequestParam("inventarioArticuloId") Long inventarioArticuloId,
                                    @RequestParam("costoAlmacenamiento") Double costoAlmacenamiento,
                                    @RequestParam("desviacion") Double desviacion,
                                    RedirectAttributes redirectAttributes) {
        inventarioArticuloService.calcularVariables(inventarioArticuloId, costoAlmacenamiento, desviacion);
        // Obtener el inventarioArticulo para obtener su inventarioId
        InventarioArticulo inventarioArticulo = inventarioArticuloService.findById(inventarioArticuloId); // Asumiendo que tienes un método para obtener el InventarioArticulo por su ID

        // Agregar inventarioId como parámetro de plantilla para la redirección
        redirectAttributes.addAttribute("inventarioId", inventarioArticulo.getInventario().getId());
        return "redirect:/inventarios/{inventarioId}/inventarioArticulos/valoresCalculados";
    }
    @GetMapping("/inventarios/{inventarioId}/inventarioArticulos/valoresCalculados")
    public String mostrarValoresCalculados(@PathVariable("inventarioId") Long inventarioId,
                                           @RequestParam(name = "inventarioArticuloId") Long inventarioArticuloId,
                                           Model model) {
        InventarioArticulo inventarioArticulo = inventarioArticuloService.findById(inventarioArticuloId);

        if (inventarioArticulo != null) {
            model.addAttribute("inventarioArticulo", inventarioArticulo);
            model.addAttribute("inventarioId", inventarioId); // Si es necesario, agregar inventarioId al modelo
            return "VistaValoresCalculados";
        } else {
            return "redirect:/maestroinventario";
        }
    }

}
