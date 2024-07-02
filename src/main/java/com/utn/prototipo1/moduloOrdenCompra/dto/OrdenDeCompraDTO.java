package com.utn.prototipo1.moduloOrdenCompra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.lang.NonNull;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrdenDeCompraDTO {

    @NonNull
    private long idProveedor;

    private List<DetalleOrdenCompraDTO> detalles;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class DetalleOrdenCompraDTO {

        @NonNull
        private float cantidad;

        @NonNull
        private long idArticulo;
    }


}
