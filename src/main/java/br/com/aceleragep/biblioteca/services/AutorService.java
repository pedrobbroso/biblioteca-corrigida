package br.com.aceleragep.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aceleragep.biblioteca.entities.AutorEntity;
import br.com.aceleragep.biblioteca.exceptions.NotFoundBusinessException;
import br.com.aceleragep.biblioteca.repositories.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;

	public AutorEntity cria(AutorEntity autorEntity) {
		return autorRepository.save(autorEntity);
	}

	public Page<AutorEntity> listaTodos(Pageable paginacao) {
		return autorRepository.findAll(paginacao);

	}

	public AutorEntity buscaPeloId(Long id) {
		return autorRepository.findById(id)
				.orElseThrow(() -> new NotFoundBusinessException("Autor de id: " + id + " não encontrado!"));
	}

	public AutorEntity atualiza(AutorEntity autorEncontrado) {
		return autorRepository.save(autorEncontrado);
	}

}
