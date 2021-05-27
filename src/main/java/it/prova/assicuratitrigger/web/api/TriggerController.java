package it.prova.assicuratitrigger.web.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.assicuratitrigger.dto.AssicuratoDTO;
import it.prova.assicuratitrigger.flusso.fileservice.CustomServiceResourceLoader;
import it.prova.assicuratitrigger.flusso.marshall.Assicurato;
import it.prova.assicuratitrigger.service.AssicuratoService;
import it.prova.assicuratitrigger.web.api.exception.AssicuratoValidationException;

@RestController
@RequestMapping("/api/trigger")
public class TriggerController {

	@Autowired
	private CustomServiceResourceLoader resourceLoader;
	@Autowired
	private AssicuratoService assicuratoServiceInstance;
	@Autowired
	private SmartValidator smartValidator;
	@Value("${file.processed}")
	private String fileProcessed;
	@Value("${file.path}")
	private String fileToDelete;
	@Value("${file.scarti}")
	private String fileScarti;
	private Date pathnamedate = new Date();

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public void eventTrigger() throws Exception {

		List<Assicurato> assicuratiPojos = resourceLoader.readAndConvertResourceData();
		List<AssicuratoDTO> assicuratiDTO = AssicuratoDTO.createAssicuratoDTOListFromPojoList(assicuratiPojos);
		
		assicuratiDTO.stream().forEach(assicuratoEntity -> {
			BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(assicuratoEntity, "assicuratoDTO");
			smartValidator.validate(assicuratoEntity, bindingResult);
			if (bindingResult.hasErrors()) {
				try {
					throw new AssicuratoValidationException("Field validation error!", bindingResult,
							new File(fileScarti + pathnamedate.toString().replaceAll(":", "-") + ".xml"),
							assicuratiDTO);
				} catch (AssicuratoValidationException e) {
					savingAndDeletingFile(assicuratiDTO, e.getFile());
					throw e;
				}
			}

		});

		List<it.prova.assicuratitrigger.model.Assicurato> assicurati = AssicuratoDTO
				.createAssicuratoModelListFromDTOList(assicuratiDTO);
		assicuratoServiceInstance.inserisciOAggiorna(assicurati);
		savingAndDeletingFile(assicuratiDTO, new File(fileProcessed + pathnamedate.toString().replaceAll(":", "-") + ".xml"));

	}

	public void savingAndDeletingFile(List<AssicuratoDTO> assicuratiDTO, File file) {
		try {
			resourceLoader.saveXml(assicuratiDTO, file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		resourceLoader.deleteFileAfterProcessing(fileToDelete);
	}
	
}
