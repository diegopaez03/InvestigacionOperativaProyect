package com.utn.prototipo1.moduloDemanda.controllers.Rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utn.prototipo1.moduloDemanda.dtos.CrearDemandaDto;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.services.DemandaService;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.services.FacturaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/restDemanda")
@CrossOrigin(origins = "*")
public class RestDemandaController {

    @Autowired
    public DemandaService demandaService;

    @Autowired
    public FacturaService facturaService;

    @PostMapping("")
    public Demanda createDemanda(@RequestBody CrearDemandaDto crearDemandaDto) {
        return demandaService.generarDemanda(crearDemandaDto);
    }

    @GetMapping("")
    public List<Demanda> getDemandas() throws Exception {
        return demandaService.findAll();
    }

    @GetMapping("/articulo/{idArticulo}")
    public List<Demanda> getDemandasByArticulo(@PathVariable("idArticulo") Long idArticulo) throws Exception {
        return demandaService.getDemandasByArticulo(idArticulo);
    }
    
    @GetMapping("year/{periodoYear}")
    public List<Demanda> getDemandasByYear(@PathVariable("periodoYear") int periodoYear) {
        return demandaService.getDemandasByYear(periodoYear);
    }
    
}
