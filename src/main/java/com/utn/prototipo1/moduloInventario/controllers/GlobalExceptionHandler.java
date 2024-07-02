package com.utn.prototipo1.moduloInventario.controllers;

import com.utn.prototipo1.moduloInventario.exceptions.StockInsuficienteException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StockInsuficienteException.class)
    public ModelAndView handleStockInsuficienteException(StockInsuficienteException ex) {
        ModelAndView modelAndView = new ModelAndView("errorStock");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}

