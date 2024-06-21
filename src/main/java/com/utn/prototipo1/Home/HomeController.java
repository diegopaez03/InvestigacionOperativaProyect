package com.utn.prototipo1.Home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "home"; // Nombre de la plantilla Thymeleaf para la página de inicio
    }



}
