package com.owly.reservation.negocio.service;

import com.owly.reservation.modelo.Cliente;
import com.owly.reservation.negocio.repository.ClienteRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase para definir los servicios de cliente
 */

@Service
@Transactional(readOnly = true) //readOnly para metodos sin anotacion transactional seran solo lectura
public class ClienteService {

    private final ClienteRepository clienteRepository;


    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Metodo para guardar un cliente
    @Transactional
    public Cliente create(Cliente cliente){
        return this.clienteRepository.save(cliente);
    }

    //Metodo para actualizar un cliente
    @Transactional
    public Cliente update(Cliente cliente){
        return this.clienteRepository.save(cliente);
    }

    //metodo para eliminar un cliente
    @Transactional
    public void delete(Cliente cliente){
        this.clienteRepository.delete(cliente);
    }

    //Consultar un cliente por su identificacion
    public Cliente findByIdentificacion(String identificacionCli){
        return this.clienteRepository.findByIdentificacion(identificacionCli);
    }
    
    public List<Cliente> findAll(){
    	return this.clienteRepository.findAll();
    }
}
