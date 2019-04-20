/**
 * 
 */
package com.owly.reservation.vista.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.owly.reservation.modelo.Cliente;
import com.owly.reservation.negocio.service.ClienteService;
import com.owly.reservation.vista.resources.vo.ClienteVO;


/**
 * Clase que represente el servicio web de Cliente
 * @author erebolledo
 *
 */

@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {
	
	private final ClienteService clienteService;
	
	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping //es una operacion post
	public ResponseEntity<Cliente> createCliente(@RequestBody ClienteVO clienteVo){
		Cliente cliente = new Cliente();
		//usamos el objeto virtual recibido por parametro para setear los valores a la entidad Cliente real
		cliente.setNombreCli(clienteVo.getNombreCli());
		cliente.setApellidoCli(clienteVo.getApellidoCli());
		cliente.setDireccionCli(clienteVo.getDireccionCli());
		cliente.setEmailCli(clienteVo.getEmailCli());
		cliente.setTelefonoCli(clienteVo.getTelefonoCli());
		return new ResponseEntity<>(this.clienteService.create(cliente),HttpStatus.CREATED);

	}
	
	@PutMapping("/{identificacion}") //es una operacion put de actualizar
	public ResponseEntity<Cliente> uodateCliente(@PathVariable("identificacion") String identificacion, ClienteVO clienteVo){
		Cliente cliente = this.clienteService.findByIdentificacion(identificacion);
		if(cliente == null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}else {
		cliente.setNombreCli(clienteVo.getNombreCli());
		cliente.setApellidoCli(clienteVo.getApellidoCli());
		cliente.setDireccionCli(clienteVo.getDireccionCli());
		cliente.setEmailCli(clienteVo.getEmailCli());
		cliente.setTelefonoCli(clienteVo.getTelefonoCli());
		return new ResponseEntity<>(this.clienteService.update(cliente),HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{identificacion}")
	public void removeCliente(@PathVariable("identificacion") String identificacion) {
		
		Cliente cliente = this.clienteService.findByIdentificacion(identificacion);
		if(cliente != null) {
			this.clienteService.delete(cliente);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
}
