package com.utn.prototipo1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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
