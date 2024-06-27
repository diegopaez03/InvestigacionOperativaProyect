package com.utn.prototipo1.moduloDemanda.controllers;


import com.utn.prototipo1.moduloDemanda.services.PrediccionDemandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class PrediccionDemandaController {

    @Autowired
    private PrediccionDemandaService prediccionDemandaServiceService;

    @GetMapping("/formulario")
    public String mostrarFormularioPrediccion() {
        return "formulario";
    }

    @PostMapping("/calcularPromedioMovil")
    public String calcularPromedioMovil(@RequestParam("demandaReal") String demandaRealStr,
                                        @RequestParam("periodos") int periodos, Model model) {
        double[] demandaReal = Arrays.stream(demandaRealStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double prediccion = prediccionDemandaServiceService.calcularPromedioMovil(demandaReal, periodos);
        model.addAttribute("metodo", "Promedio Móvil");
        model.addAttribute("prediccion", prediccion);
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

        double prediccion = prediccionDemandaServiceService.calcularPromedioPonderado(demandaReal, coeficientes);
        model.addAttribute("metodo", "Promedio Ponderado");
        model.addAttribute("prediccion", prediccion);
        return "resultado";
    }

    @PostMapping("/calcularSuavizacionExponencial")
    public String calcularSuavizacionExponencial(@RequestParam("demandaReal") String demandaRealStr,
                                                 @RequestParam("alfa") double alfa, Model model) {
        double[] demandaReal = Arrays.stream(demandaRealStr.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double prediccion = prediccionDemandaServiceService.calcularSuavizacionExponencial(demandaReal, alfa);
        model.addAttribute("metodo", "Suavización Exponencial");
        model.addAttribute("prediccion", prediccion);
        return "resultado";
    }


}
