package br.com.aceleragep.biblioteca.dto.inputs;

import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroInput {

	@Length(max = 200, message = "O título do livro deve conter no máximo 200 carácteres!")
	@NotBlank(message = "O título é obrigatório!")
	private String titulo;
	
	@DecimalMax("9999")
	@NotNull(message = "O ano de lançamento é obrigatório!")
	private Integer anoLancamento;
	
	@NotEmpty(message = "Deve ter pelo menos um autor!")
	private List<Long> idsAutores;
	
}
