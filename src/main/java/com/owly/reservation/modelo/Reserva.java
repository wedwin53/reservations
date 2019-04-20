package com.owly.reservation.modelo;

/*
 * Clase que representa la tabla de reservas
 */

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idRes;
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoRes;
    @Temporal(TemporalType.DATE)
    private Date fechaSalidaRes;
    private int cantidadPersonasRes;
    private String descripcionRes;
    @ManyToOne
    @JoinColumn(name = "idCli")
    private Cliente cliente;
}
