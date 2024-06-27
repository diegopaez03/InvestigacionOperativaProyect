package com.utn.prototipo1.moduloDemanda.dtos;

import java.util.Date;

import org.springframework.lang.NonNull;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CrearDemandaDto {

    @NonNull
    private Date fechaDesde;

    @NonNull
    private Date fechaHasta;

    @NonNull
    private Long idArticulo;
}
