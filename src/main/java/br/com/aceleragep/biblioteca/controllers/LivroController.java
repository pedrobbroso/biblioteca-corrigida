package br.com.aceleragep.biblioteca.controllers;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aceleragep.biblioteca.config.ControllerConfig;
import br.com.aceleragep.biblioteca.converts.LivroConvert;
import br.com.aceleragep.biblioteca.dto.inputs.LivroInput;
import br.com.aceleragep.biblioteca.entities.LivroEntity;
import br.com.aceleragep.biblioteca.outputs.LivroOutput;
import br.com.aceleragep.biblioteca.services.LivroService;

@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/livros")
public class LivroController {
	
	@Autowired
	private LivroConvert livroConvert;
	
	@Autowired
	private LivroService livroService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public LivroOutput cria(@RequestBody @Valid LivroInput livroInput) {
		LivroEntity livroEntity = livroConvert.inputToNewEntity(livroInput);
		LivroEntity livroCriado = livroService.cria(livroEntity, livroInput);
		return livroConvert.entityToOutput(livroCriado);
	}
	
	@GetMapping
	public Page<LivroOutput> listaLivros(@ParameterObject @PageableDefault(page = 0, size = 5, sort = "titulo", direction = Direction.ASC) Pageable paginacao) {
		Page<LivroEntity> listaTodos = livroService.listaTodos(paginacao);
		return livroConvert.listPageEntityToListPageOutput(listaTodos);
	}
	
	@GetMapping("/{id}")
	public LivroOutput buscaPeloId(@PathVariable Long id) {
		LivroEntity livro = livroService.buscaPeloId(id);
		return livroConvert.entityToOutput(livro);
	}
	
	@PutMapping("/{id}")
	public LivroOutput alterar(@PathVariable Long id, @RequestBody LivroInput livroInput) { 
		LivroEntity livroLocalizado = livroService.buscaPeloId(id);
		livroConvert.copyInputToEntity(livroInput, livroLocalizado);
		LivroEntity livroAlterado = livroService.altera(livroLocalizado, livroInput);
		return livroConvert.entityToOutput(livroAlterado);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		LivroEntity livro = livroService.buscaPeloId(id);
		livroService.deleta(livro);
	}
	
}
