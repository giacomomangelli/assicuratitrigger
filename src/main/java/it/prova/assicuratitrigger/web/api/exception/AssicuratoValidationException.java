package it.prova.assicuratitrigger.web.api.exception;

import java.io.File;
import java.util.List;

import org.springframework.validation.BeanPropertyBindingResult;

import it.prova.assicuratitrigger.dto.AssicuratoDTO;

public class AssicuratoValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private File file;

	public AssicuratoValidationException(String message, BeanPropertyBindingResult bindingResult, File file,
			List<AssicuratoDTO> assicuratiDTO) {
		super(message);
		this.file = file;

	}
	
	public File getFile() {
		return this.file;
	}
}
