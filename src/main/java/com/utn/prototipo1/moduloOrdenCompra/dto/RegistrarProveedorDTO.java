package com.utn.prototipo1.moduloOrdenCompra.dto;

import java.util.List;

import org.springframework.lang.NonNull;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistrarProveedorDTO {

    @NonNull
    private String nombreProveedor;

    private List<ProveedorArticuloDTO> proveedorArticulos;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class ProveedorArticuloDTO {

        @NonNull
        private Long idArticulo;

        @NonNull
        private double costoPedido;

        @NonNull
        private int tiempoDemoraArticulo;
    }
}
