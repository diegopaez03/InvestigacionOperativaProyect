package com.utn.prototipo1.moduloVenta.controllers;


import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
        Factura createdFactura = facturaService.createFactura(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFactura);
    }

    @PostMapping("/{NRO_Factura}/detalles")
    public ResponseEntity<String> addDetalleFactura(@PathVariable("NRO_Factura") Long nroFactura, @RequestBody List<DetalleFactura> detalles) {
        facturaService.addDetalleFactura(nroFactura, detalles);
        return ResponseEntity.status(HttpStatus.CREATED).body("Detalles de factura agregados exitosamente");
    }
}
