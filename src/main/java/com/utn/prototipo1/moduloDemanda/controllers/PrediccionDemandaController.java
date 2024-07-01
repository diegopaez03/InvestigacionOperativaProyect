package com.utn.prototipo1.moduloDemanda.controllers;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.services.DemandaService;
import com.utn.prototipo1.moduloDemanda.services.PrediccionDemandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Controller
public class PrediccionDemandaController {

    @Autowired
    private PrediccionDemandaService prediccionDemandaService;

    @Autowired
    DemandaService demandaService;

    @Autowired
    ArticuloService articuloService;

    @GetMapping("/formulario")
    public String mostrarFormularioPrediccion() {
        return "formulario";
    }

    /*@PostMapping("/calcularPromedioMovil")
    public String calcularPromedioMovil(@RequestParam("demandaReal") String demandaRealStr,
                                        @RequestParam("periodos") int periodos, Model model) {
        double[] demandaReal = Arrays.stream(demandaRealStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double prediccion = prediccionDemandaService.calcularPromedioMovil(demandaReal, periodos);
        model.addAttribute("metodo", "Promedio Móvil");
        model.addAttribute("prediccion", prediccion);
        prediccionDemandaService.guardarPrediccion(demandaRealStr, prediccion);
        return "resultado";
    }



    @PostMapping("/calcularPromedioPonderado")
    public String calcularPromedioPonderado(@RequestParam("demandaReal") String demandaRealStr,
                                            @RequestParam("coeficientes") String coeficientesStr, Model model) {
        double[] demandaReal = Arrays.stream(demandaRealStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();
        double[] coeficientes = Arrays.stream(coeficientesStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double prediccion = prediccionDemandaService.calcularPromedioPonderado(demandaReal, coeficientes);
        model.addAttribute("metodo", "Promedio Ponderado");
        model.addAttribute("prediccion", prediccion);
        prediccionDemandaService.guardarPrediccion(demandaRealStr, prediccion);
        return "resultado";
    }

    @PostMapping("/calcularSuavizacionExponencial")
    public String calcularSuavizacionExponencial(@RequestParam("demandaReal") String demandaRealStr,
                                                 @RequestParam("alfa") double alfa, Model model) {
        double[] demandaReal = Arrays.stream(demandaRealStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double prediccion = prediccionDemandaService.calcularSuavizacionExponencial(demandaReal, alfa);
        model.addAttribute("metodo", "Suavización Exponencial");
        model.addAttribute("prediccion", prediccion);
        prediccionDemandaService.guardarPrediccion(demandaRealStr, prediccion);
        return "resultado";
    }*/


    @PostMapping("/calcularPromedioMovil")
    public String calcularPromedioMovil(@RequestParam("demandaReal") String demandaRealStr,
                                        @RequestParam("periodos") int periodos,
                                        Model model) {
        double[] demandaReal = Arrays.stream(demandaRealStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double prediccion = prediccionDemandaService.calcularPromedioMovil(demandaReal, periodos);

        double[] predicciones = new double[demandaReal.length];
        Arrays.fill(predicciones, prediccion);

        double error = prediccionDemandaService.calcularErrorPrediccion(demandaReal, predicciones);
        double errorAbsoluto = prediccionDemandaService.calcularErrorAbsolutoPrediccion(demandaReal, predicciones);

        model.addAttribute("metodo", "Promedio Móvil");
        model.addAttribute("prediccion", prediccion);
        model.addAttribute("error", error);
        model.addAttribute("errorAbsoluto", errorAbsoluto);
        prediccionDemandaService.guardarPrediccion(demandaRealStr, prediccion);

        return "resultado"; // Devolver a la página de resultados
    }

    @PostMapping("/calcularPromedioPonderado")
    public String calcularPromedioPonderado(@RequestParam("demandaReal") String demandaRealStr,
                                            @RequestParam("coeficientes") String coeficientesStr,
                                            Model model) {
        double[] demandaReal = Arrays.stream(demandaRealStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();
        double[] coeficientes = Arrays.stream(coeficientesStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double prediccion = prediccionDemandaService.calcularPromedioPonderado(demandaReal, coeficientes);

        double[] predicciones = new double[demandaReal.length];
        Arrays.fill(predicciones, prediccion);

        double error = prediccionDemandaService.calcularErrorPrediccion(demandaReal, predicciones);
        double errorAbsoluto = prediccionDemandaService.calcularErrorAbsolutoPrediccion(demandaReal, predicciones);

        model.addAttribute("metodo", "Promedio Ponderado");
        model.addAttribute("prediccion", prediccion);
        model.addAttribute("error", error);
        model.addAttribute("errorAbsoluto", errorAbsoluto);
        prediccionDemandaService.guardarPrediccion(demandaRealStr, prediccion);

        return "resultado"; // Devolver a la página de resultados
    }

    @PostMapping("/calcularSuavizacionExponencial")
    public String calcularSuavizacionExponencial(@RequestParam("demandaReal") String demandaRealStr,
                                                 @RequestParam("alfa") double alfa,
                                                 Model model) {
        double[] demandaReal = Arrays.stream(demandaRealStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double prediccion = prediccionDemandaService.calcularSuavizacionExponencial(demandaReal, alfa);

        double[] predicciones = new double[demandaReal.length];
        Arrays.fill(predicciones, prediccion);

        double error = prediccionDemandaService.calcularErrorPrediccion(demandaReal, predicciones);
        double errorAbsoluto = prediccionDemandaService.calcularErrorAbsolutoPrediccion(demandaReal, predicciones);

        model.addAttribute("metodo", "Suavización Exponencial");
        model.addAttribute("prediccion", prediccion);
        model.addAttribute("error", error);
        model.addAttribute("errorAbsoluto", errorAbsoluto);
        prediccionDemandaService.guardarPrediccion(demandaRealStr, prediccion);

        return "resultado"; // Devolver a la página de resultados
    }


}
