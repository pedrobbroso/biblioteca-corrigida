package br.com.aceleragep.biblioteca.controllers;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aceleragep.biblioteca.config.ControllerConfig;
import br.com.aceleragep.biblioteca.converts.AutorConvert;
import br.com.aceleragep.biblioteca.converts.LivroConvert;
import br.com.aceleragep.biblioteca.dto.inputs.AutorInput;
import br.com.aceleragep.biblioteca.dto.outputs.AutorOutput;
import br.com.aceleragep.biblioteca.entities.AutorEntity;
import br.com.aceleragep.biblioteca.entities.LivroEntity;
import br.com.aceleragep.biblioteca.outputs.LivroAutorOutput;
import br.com.aceleragep.biblioteca.services.AutorService;
import br.com.aceleragep.biblioteca.services.LivroService;

@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/autores")
public class AutorController {

	@Autowired
	private AutorConvert autorConvert;
	
	@Autowired
	private AutorService autorService;
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private LivroConvert livroConvert;
	

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public AutorOutput cadastrarAutor(@RequestBody @Valid AutorInput autorInput) {
		AutorEntity autorEntity = autorConvert.inputToNewEntity(autorInput);
		AutorEntity autorCriado = autorService.cria(autorEntity);
		return autorConvert.entityToOutput(autorCriado);
	}

	@GetMapping
	public Page<AutorOutput> listaAutores(
			@ParameterObject @PageableDefault(page = 0, size = 5, sort = "nome", direction = Direction.ASC) Pageable paginacao) {
		Page<AutorEntity> listaTodos = autorService.listaTodos(paginacao);
		return autorConvert.listPageEntityToListPageOutput(listaTodos);
	}

	@GetMapping("/{id}")
	public AutorOutput buscaPorId(@PathVariable Long id) {
		AutorEntity autorEncontrado = autorService.buscaPeloId(id);
		return autorConvert.entityToOutput(autorEncontrado);
	}

	@PutMapping("/{id}")
	public AutorOutput atualiza(@PathVariable Long id, @RequestBody @Valid AutorInput autorInput) {
		AutorEntity autorEncontrado = autorService.buscaPeloId(id);
		autorConvert.copyInputToEntity(autorInput, autorEncontrado);
		AutorEntity autorAtualizado = autorService.atualiza(autorEncontrado);
		return autorConvert.entityToOutput(autorAtualizado);
	}

	@GetMapping("/{id}/livros")
	public Page<LivroAutorOutput> buscaLivroPeloIdDoAutor(@PathVariable Long id,
			@ParameterObject @PageableDefault(page = 0, size = 5, sort = "titulo", direction = Direction.ASC) Pageable paginacao) {
		autorService.buscaPeloId(id);
		Page<LivroEntity> livros = livroService.buscaPeloIdAutor(id, paginacao);
		return livroConvert.listPageEntityToListPageOutputCopy(livros);
	}

}
