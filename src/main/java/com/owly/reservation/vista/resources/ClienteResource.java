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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Clase que represente el servicio web de Cliente
 * @author erebolledo
 *
 */

@RestController
@RequestMapping("/api/cliente")
@Api(tags = "cliente")
public class ClienteResource {
	
	private final ClienteService clienteService;
	
	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping //es una operacion post o de creacion
	@ApiOperation(value = "Crear Cliente", notes = "Servicio para crear un nuevo cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente Creado Correctamente"),
							@ApiResponse(code = 400, message = "Solicitud Invalida")})
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
	@ApiOperation(value = "Actualizar Cliente", notes = "Servicio para actualizar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente actualizado Correctamente"),
							@ApiResponse(code = 404, message = "Cliente no encontrado")})
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
	@ApiOperation(value = "Eliminar Cliente", notes = "Servicio para Eliminar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente Eliminado Correctamente"),
							@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public void removeCliente(@PathVariable("identificacion") String identificacion) {
		
		Cliente cliente = this.clienteService.findByIdentificacion(identificacion);
		if(cliente != null) {
			this.clienteService.delete(cliente);
		}
		
	}
	
	@GetMapping
	@ApiOperation(value = "Lista Clientes", notes = "Servicio para listar todos los clientes")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Clientes encontrados"),
							@ApiResponse(code = 404, message = "Clientes No encontrados")})
	public ResponseEntity<List<Cliente>> findAll(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
}
