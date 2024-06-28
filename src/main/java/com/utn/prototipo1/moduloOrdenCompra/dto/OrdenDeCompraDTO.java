package com.utn.prototipo1.moduloOrdenCompra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdenDeCompraDTO {

    @NonNull
    private long idProveedor;

    private List<DetalleOrdenCompraDTO> detalles;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DetalleOrdenCompraDTO {

        @NonNull
        private float cantidad;

        @NonNull
        private long idArticulo;
    }


}
