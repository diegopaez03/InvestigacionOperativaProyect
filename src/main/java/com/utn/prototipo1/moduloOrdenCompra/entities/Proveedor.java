package com.utn.prototipo1.moduloOrdenCompra.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "proveedor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Proveedor extends BaseEntidad {


    private int codProveedor;

    private String nombreProveedor;

    @Column(name = "fecha_bajaProveedor")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBajaProveedor;

}
