package com.utn.prototipo1.moduloDemanda.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloDemanda.entities.PrediccionDemanda;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PrediccionDemandaService {

    double calcularPromedioMovil(double[] demandaReal, int periodos);

    void guardarPrediccion(String demandaReal, double demandaPredicha);

    List<PrediccionDemanda> obtenerTodasLasPredicciones();

    double calcularPromedioPonderado(double[] demandaReal, double[] coeficientes);

    double calcularSuavizacionExponencial(double[] demandaReal, double alfa);

    double calcularErrorPrediccion(double[] demandaReal, double[] predicciones);

    double calcularErrorAbsolutoPrediccion(double[] demandaReal, double[] predicciones);

}
