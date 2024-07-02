package com.utn.prototipo1.moduloDemanda.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.entities.PrediccionDemanda;
import com.utn.prototipo1.moduloDemanda.repositories.PrediccionDemandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PrediccionDemandaServiceImpl implements PrediccionDemandaService{


    @Autowired
    PrediccionDemandaRepository prediccionDemandaRepository;

    @Autowired
    DemandaService demandaService;


    @Override
    public double calcularPromedioMovil(double[] demandaReal, int periodos) {
        if (demandaReal == null || demandaReal.length == 0 || periodos <= 0 || periodos > demandaReal.length) {
            throw new IllegalArgumentException("Los datos de entrada no son válidos para el método de Promedio Móvil.");
        }

        double suma = 0;
        for (int i = demandaReal.length - periodos; i < demandaReal.length; i++) {
            suma += demandaReal[i];
        }

        return suma / periodos;
    }



   @Override
    public double calcularPromedioPonderado(double[] demandaReal, double[] coeficientes) {
        if (demandaReal == null || coeficientes == null || demandaReal.length != coeficientes.length) {
            throw new IllegalArgumentException("Los datos de entrada no son válidos para el método de Promedio Ponderado.");
        }


        double sumaPonderada = 0;
        double sumaCoeficientes = 0;

        for (int i = 0; i < demandaReal.length; i++) {
            sumaPonderada += demandaReal[i] * coeficientes[i];
            sumaCoeficientes += coeficientes[i];
        }

        return sumaPonderada / sumaCoeficientes;
    }


    @Override
    public double calcularSuavizacionExponencial(double[] demandaReal, double alfa) {
        if (demandaReal == null || demandaReal.length == 0 || alfa < 0 || alfa > 1) {
            throw new IllegalArgumentException("Los datos de entrada no son válidos para el método de Suavización Exponencial.");
        }

        double prediccion = demandaReal[0]; // Inicialización con el primer dato real

        for (int i = 1; i < demandaReal.length; i++) {
            prediccion = alfa * demandaReal[i] + (1 - alfa) * prediccion;
        }

        return prediccion;
    }

    @Override
    public void guardarPrediccion(String demandaReal, double demandaPredicha) {
        PrediccionDemanda nuevaPrediccion = new PrediccionDemanda();
        nuevaPrediccion.setDemandaReal(demandaReal);
        nuevaPrediccion.setDemandaPredicha(demandaPredicha);
        prediccionDemandaRepository.save(nuevaPrediccion);
    }



    @Override
    public List<PrediccionDemanda> obtenerTodasLasPredicciones() {
        return prediccionDemandaRepository.findAll();
    }


    @Override
    public double calcularErrorPrediccion(double[] demandaReal, double[] predicciones) {
        double error = 0.0;
        int length = Math.min(demandaReal.length, predicciones.length);
        for (int i = 0; i < length; i++) {
            error += demandaReal[i] - predicciones[i];
        }
        return error;
    }

    @Override
    public double calcularErrorAbsolutoPrediccion(double[] demandaReal, double[] predicciones) {
        double errorAbsoluto = 0.0;
        int length = Math.min(demandaReal.length, predicciones.length);
        for (int i = 0; i < length; i++) {
            errorAbsoluto += Math.abs(demandaReal[i] - predicciones[i]);
        }
        return errorAbsoluto;
    }



}
