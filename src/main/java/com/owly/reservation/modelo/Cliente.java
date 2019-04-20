package com.owly.reservation.modelo;

/*
 * Clase que representa la tabla Clientes
 */

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

//la anotacion Data ayuda a crear los getter y setters en el momento de la construccion del proyecto

@Data
@Entity
@Table(name = "cliente")
@NamedQuery(name="Cliente.findByIdentificacion", query = "select c from Cliente c where c.identificacionCli = ?1 ")
public class Cliente {
    //al usar lombok no es necesario encapsular los campos de la clase con el private
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2") //se usa con postgres
    private String idCli;
    private String nombreCli;
    private String apellidoCli;
    private String identificacionCli;
    private String direccionCli;
    private String telefonoCli;
    private String emailCli;

    @OneToMany(mappedBy = "cliente")
    private Set<Reserva> reservas;

    public Cliente() {

    }
}
