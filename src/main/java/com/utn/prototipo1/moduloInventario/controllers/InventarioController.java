package com.utn.prototipo1.moduloInventario.controllers;

import com.utn.prototipo1.Base.controllers.BaseControllerImpl;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
import com.utn.prototipo1.moduloInventario.services.InventarioServices;
import com.utn.prototipo1.moduloInventario.services.InventarioServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class InventarioController {

    @Autowired
    private InventarioServices inventarioServices;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private InventarioArticuloService inventarioArticuloService;

    //Listar los inventarios que se crean
    @GetMapping("/maestroinventario")
    public String mostrarTodosLosInventarios(Model model) {
        model.addAttribute("inventarios", inventarioServices.findAll());
        return "MaestroInventario";
    }

    @GetMapping("/maestroinventario/nuevo")
    public String mostrarFormularioCrearInventario(Model model) {
        Inventario inventario = new Inventario();
        inventario.getInventarioArticulos().add(new InventarioArticulo()); // Agrega un detalle de factura por defecto
        model.addAttribute("inventario", inventario);
        model.addAttribute("articulos", articuloService.getAllArticulos());
        return "crear-inventario";
    }

    @PostMapping("/inventarios")
    public String crearInventario(@ModelAttribute("inventario") Inventario inventario) {
        inventario.setFechaDesde(LocalDate.now()); // Asignar la fecha y hora actual
        for (InventarioArticulo inventarioArticulo : inventario.getInventarioArticulos()) {
            inventarioArticulo.setInventario(inventario);// Establece la relación entre la factura y sus detalles
        }
        inventarioServices.crearInventario(inventario);
        return "redirect:/maestroinventario";
    }

    //borrar el inventario
    @GetMapping("/maestroinventario/{id}")
    public String eliminarInventario(@PathVariable("id") Long id) {
        inventarioServices.deleteById(id);
        return "redirect:/maestroinventario";
    }

    // DETALLE FACTURA ------------------------------

    @GetMapping("/inventarios/{inventarioId}/inventarioArticulos/nuevo")
    public String mostrarFormularioCrearInventarioArticulo(@PathVariable("inventarioId") Long InventarioId, Model model) {
        Inventario inventario = inventarioServices.obtenerInventarioPorId(InventarioId);
        List<Articulo> articulos = articuloService.getAllArticulos();
        InventarioArticulo inventarioArticulo = new InventarioArticulo();
        inventarioArticulo.setInventario(inventario);
        model.addAttribute("inventarioArticulo", inventarioArticulo);
        model.addAttribute("articulos", articulos);
        model.addAttribute("inventario", inventario);
        return "crear-inventario-articulo";
    }

    @PostMapping("/inventarios/{inventarioId}/inventarioArticulos")
    public String crearinventarioArticulo(@PathVariable("inventarioId") Long InventarioId,
                                          @ModelAttribute("inventarioArticulo") InventarioArticulo inventarioArticulo,
                                          @RequestParam("articulo.id") Long articuloId) {
        Inventario inventario = inventarioServices.obtenerInventarioPorId(InventarioId);
        Articulo articulo = articuloService.getArticuloById(articuloId);
        inventarioArticulo.setInventario(inventario);
        inventarioArticulo.setArticulo(articulo);
        inventarioArticuloService.save(inventarioArticulo);
        return "redirect:/maestroinventario";
    }

    @GetMapping("/inventarios/{inventarioId}/inventarioArticulos")
    public String verInventarioArticulo(@PathVariable("inventarioId") Long InventarioId, Model model) {
        List<InventarioArticulo> inventarioArticulos = inventarioArticuloService.obtenerArticuloPorInventario(InventarioId);
        model.addAttribute("inventarioArticulo", inventarioArticulos);
        return "MaestroInventarioArticulo";
    }

    @GetMapping("/inventarios/{inventarioId}/inventarioArticulos/calcularVariablesForm/{id}")
    public String showCalcularVariablesForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("inventarioArticuloId", id);
        return "calcularVariableForm"; // Devuelve la vista correcta
    }

    @PostMapping("/calcularVariables")
    public String calcularVariables(@RequestParam Long inventarioArticuloId,
                                    @RequestParam Double costoAlmacenamiento,
                                    @RequestParam Double desviacion,
                                    RedirectAttributes redirectAttributes) {
        // Lógica para calcular variables, similar a lo que ya tienes

        // Después de calcular las variables, obtén el inventarioId del inventarioArticulo
        InventarioArticulo inventarioArticulo = inventarioArticuloService.findById(inventarioArticuloId);
        Long inventarioId = inventarioArticulo.getInventario().getId();

        // Agrega los atributos para el redireccionamiento
        redirectAttributes.addAttribute("inventarioId", inventarioId);
        redirectAttributes.addAttribute("inventarioArticuloId", inventarioArticuloId);

        // Redirecciona a la URL con los parámetros adecuados
        return "redirect:/inventarios/" + inventarioId + "/inventarioArticulos/valoresCalculados/" + inventarioArticuloId;
    }

    @GetMapping("/inventarios/{inventarioId}/inventarioArticulos/valoresCalculados/{inventarioArticuloId}")
    public String showValoresCalculados(@PathVariable Long inventarioId,
                                        @PathVariable Long inventarioArticuloId,
                                        Model model) {
        // Lógica para mostrar los valores calculados del inventarioArticulo
        InventarioArticulo inventarioArticulo = inventarioArticuloService.findById(inventarioArticuloId);
        model.addAttribute("inventarioArticulo", inventarioArticulo);
        return "VistaValoresCalculados";
    }
}