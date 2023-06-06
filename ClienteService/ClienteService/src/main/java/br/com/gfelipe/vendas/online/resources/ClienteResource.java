package br.com.gfelipe.vendas.online.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfelipe.vendas.online.domain.Cliente;
import br.com.gfelipe.vendas.online.usecase.BuscaCliente;
import br.com.gfelipe.vendas.online.usecase.CadastroCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {
	
	
	private BuscaCliente buscaCliente;
	private CadastroCliente cadastroCliente;
	
	@Autowired
	public ClienteResource( BuscaCliente buscaCliente,
			CadastroCliente cadastroCliente){
		this.buscaCliente = buscaCliente;
		this.cadastroCliente= cadastroCliente;
		
	}
	
	@GetMapping
	public ResponseEntity<Page<Cliente>> buscar(@Parameter(description="Objeto de paginação") Pageable pageable) {
		return ResponseEntity.ok(buscaCliente.buscar(pageable));
	}
	
	@GetMapping(value = "isCadastrado/{id}")
	@Operation(summary = "Busca um cliente pelo id")
	public ResponseEntity<Boolean> isCadastrado(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(buscaCliente.isCadastrado(id));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente){
		return ResponseEntity.ok(cadastroCliente.cadastrar(cliente));
		
	}
	
	@PutMapping
	@Operation(summary = "Atualiza um cliente")
	public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.ok(cadastroCliente.atualizar(cliente));
		
	}	
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove um cliente pelo seu identificador único")
	public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
		cadastroCliente.remover(id);
		return ResponseEntity.ok("Removido com sucesso");
	}
	
	
	
	
	
	

}
