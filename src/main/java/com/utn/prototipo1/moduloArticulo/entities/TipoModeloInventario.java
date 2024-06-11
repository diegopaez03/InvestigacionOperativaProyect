package com.utn.prototipo1.moduloArticulo.entities;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tipoModeloInventario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class TipoModeloInventario extends BaseEntidad {


    private String nombre;
    private String fechaBajaTMI;

}
