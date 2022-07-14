package br.com.aceleragep.biblioteca.converts;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.aceleragep.biblioteca.dto.inputs.LivroInput;
import br.com.aceleragep.biblioteca.entities.LivroEntity;
import br.com.aceleragep.biblioteca.outputs.LivroAutorOutput;
import br.com.aceleragep.biblioteca.outputs.LivroOutput;

@Component
public class LivroConvert {

	@Autowired
	private ModelMapper modelMapper;
	
	public LivroEntity inputToNewEntity(@Valid LivroInput livroInput) {
		return modelMapper.map(livroInput, LivroEntity.class);
	}

	public LivroOutput entityToOutput(LivroEntity livroCriado) {
		return modelMapper.map(livroCriado, LivroOutput.class);
	}

	public Page<LivroOutput> listPageEntityToListPageOutput(Page<LivroEntity> listaTodos) {
		return listaTodos.map(this::entityToOutput);
	}

	public void copyInputToEntity(LivroInput livroInput, LivroEntity livroLocalizado) {
		modelMapper.map(livroInput, livroLocalizado);
	}

	public Page<LivroAutorOutput> listPageEntityToListPageOutputCopy(Page<LivroEntity> livros) {
		return livros.map(this::entityToSemAutorOutput);
	}

	private <U> LivroAutorOutput entityToSemAutorOutput(LivroEntity livroEntity) {
		return modelMapper.map(livroEntity, LivroAutorOutput.class);
	}

}
