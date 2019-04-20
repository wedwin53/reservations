package com.owly.reservation.negocio.repository;


/*
 * Interfaz para definir  las operaciones bdd relacionadas con el cliente
 */

import com.owly.reservation.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository recibe parametros: la clase que usara como entity de la bd y el tipo de valor del id de esa clase o tabla
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    /**
     * Definicion de metodo para buscar al cliente por su apellido, al el findBy es palabra reservada luego le sigue
     * el nombre del atributo de la clase (apellidocli)
     * @param apellidoCli
     * @return
     */
    public List<Cliente> findByApellidoCli(String apellidoCli);

    public Cliente findByIdentificacion(String identificacionCli);

}
