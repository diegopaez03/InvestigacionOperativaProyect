package com.utn.prototipo1.moduloDemanda.controllers.Rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utn.prototipo1.moduloDemanda.dtos.CrearDemandaDto;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.services.DemandaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/restDemanda")
@CrossOrigin(origins = "*")
public class RestDemandaController {

    @Autowired
    public DemandaService demandaService;

    @PostMapping("")
    public Demanda createDemanda(@RequestBody CrearDemandaDto crearDemandaDto) {
        return demandaService.generarDemanda(crearDemandaDto);
    }
    
}
