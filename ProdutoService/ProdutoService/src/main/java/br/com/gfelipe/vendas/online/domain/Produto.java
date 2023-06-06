package br.com.gfelipe.vendas.online.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(collection = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name="Produto", description="Produto") 
public class Produto {
	
	public enum Status {
		ATIVO, INATIVO;
	}
	
	@Id
	@Schema(description="Identificador único") 
	private String id;

	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
	private String codigo;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="Nome", minLength = 1, maxLength=50, nullable = false) 
	private String nome;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="descricao", minLength = 1, maxLength=150, nullable = false)
	private String descricao;
	
	@NotNull
	@Schema(description="Valor do produto", nullable = false) 
	private BigDecimal valor;
	
	@NotNull
	@Schema(description="Status do produto", nullable = false) 
	private Status status;

}
