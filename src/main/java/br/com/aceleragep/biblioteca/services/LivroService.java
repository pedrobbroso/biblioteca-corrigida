package br.com.aceleragep.biblioteca.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aceleragep.biblioteca.dto.inputs.LivroInput;
import br.com.aceleragep.biblioteca.entities.AutorEntity;
import br.com.aceleragep.biblioteca.entities.LivroEntity;
import br.com.aceleragep.biblioteca.exceptions.NotFoundBusinessException;
import br.com.aceleragep.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorService autorService;

	public LivroEntity cria(LivroEntity livroEntity, @Valid LivroInput livroInput) {
		// Criar uma lista de autorEntity, fazer um for para cada id do livroInput
		List<AutorEntity> autores = new ArrayList<>();

		for (Long idAutor : livroInput.getIdsAutores()) {
			AutorEntity autor = autorService.buscaPeloId(idAutor);
			autores.add(autor);
		}

		livroEntity.setAutores(autores);
		return livroRepository.save(livroEntity);

	}

	public Page<LivroEntity> listaTodos(Pageable paginacao) {
		return livroRepository.findAll(paginacao);
	}

	public LivroEntity buscaPeloId(Long id) {
		return livroRepository.findById(id)
				.orElseThrow(() -> new NotFoundBusinessException("O livro de id: " + id + " não foi encontrado!"));
	}

	public LivroEntity altera(LivroEntity livroLocalizado, LivroInput livroInput) {
		List<AutorEntity> autores = new ArrayList<>();

		for (Long idAutor : livroInput.getIdsAutores()) {
			AutorEntity autor = autorService.buscaPeloId(idAutor);
			autores.add(autor);
		}

		livroLocalizado.setAutores(autores);
		return livroRepository.save(livroLocalizado);
	}

	public void deleta(LivroEntity livro) {
		livroRepository.delete(livro);
	}

	public Page<LivroEntity> buscaPeloIdAutor(Long id, Pageable paginacao) {
		return livroRepository.findAllByAutoresId(id, paginacao);
	}

}
