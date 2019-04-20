package com.owly.reservation.vista.resources.vo;

/*
 * Clase que representa virtualmente el objeto Cliente, pero sin anotaciones ni relaciones a base de datos, es un objeto puro
 */

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
public class ClienteVO {
    
    private String idCli;
    private String nombreCli;
    private String apellidoCli;
    private String identificacionCli;
    private String direccionCli;
    private String telefonoCli;
    private String emailCli;

    
}
