package it.prova.assicuratitrigger.web.api.exception;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = AssicuratoValidationException.class)
	protected ResponseEntity<Object> handleOperations(RuntimeException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.NOT_ACCEPTABLE);
		body.put("message", ex.getMessage());
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
	}	
	
	@ExceptionHandler(value = FileNotFoundException.class)
	protected ResponseEntity<Object> fileNotFound(RuntimeException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.NOT_FOUND);
		body.put("message", ex.getMessage());
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}


}
