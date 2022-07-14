package br.com.aceleragep.biblioteca.converts;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.aceleragep.biblioteca.dto.inputs.AutorInput;
import br.com.aceleragep.biblioteca.dto.outputs.AutorOutput;
import br.com.aceleragep.biblioteca.entities.AutorEntity;

@Component
public class AutorConvert {

	@Autowired
	private ModelMapper modelMapper;
	
	public AutorEntity inputToNewEntity(@Valid AutorInput autorInput) {
		return modelMapper.map(autorInput, AutorEntity.class);
	}

	public AutorOutput entityToOutput(AutorEntity autorCriado) {
		return modelMapper.map(autorCriado, AutorOutput.class);
	}

	public Page<AutorOutput> listPageEntityToListPageOutput(Page<AutorEntity> listaTodos) {
		return listaTodos.map(this::entityToOutput);
	}

	public void copyInputToEntity(@Valid AutorInput autorInput, AutorEntity autorEncontrado) {
		modelMapper.map(autorInput, autorEncontrado);
	}

}
