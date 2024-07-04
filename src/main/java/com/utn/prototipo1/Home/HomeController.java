package com.utn.prototipo1.Home;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController implements ErrorController{

    @GetMapping("/")
    public String home(Model model) {
        return "home"; // Nombre de la plantilla Thymeleaf para la página de inicio
    }

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("error");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = (throwable != null) ? throwable.getMessage() : "N/A";

        mav.addObject("status", statusCode);
        mav.addObject("error", "Error");
        mav.addObject("message", errorMessage);
        mav.addObject("timestamp", new java.util.Date());
        mav.addObject("path", request.getRequestURI());

        return mav;
    }

    public String getErrorPath() {
        return "/error";
    }



}
