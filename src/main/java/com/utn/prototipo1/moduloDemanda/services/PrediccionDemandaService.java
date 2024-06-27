package com.utn.prototipo1.moduloDemanda.services;

import org.springframework.stereotype.Service;

public interface PrediccionDemandaService {

    double calcularPromedioMovil(double[] demandaReal, int periodos);


    double calcularPromedioPonderado(double[] demandaReal, double[] coeficientes);

    double calcularSuavizacionExponencial(double[] demandaReal, double alfa);

}
