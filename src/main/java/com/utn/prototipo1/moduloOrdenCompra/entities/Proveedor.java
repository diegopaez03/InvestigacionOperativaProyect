package com.utn.prototipo1.moduloOrdenCompra.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Proveedor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Proveedor extends BaseEntidad {

    private int codProveedor;

    private String nombreProveedor;

    @Column(name = "fecha_bajaProveedor", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBajaProveedor;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "codProveedor")
    private List<ProveedorArticulo> proveedorArticulo;

}
