package it.prova.assicuratitrigger.flusso.fileservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import it.prova.assicuratitrigger.dto.AssicuratoDTO;
import it.prova.assicuratitrigger.flusso.marshall.Assicurati;
import it.prova.assicuratitrigger.flusso.marshall.Assicurato;

@Component
public class CustomServiceResourceLoader implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	
	@Value("${file.path}")
	private String filepath;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public List<Assicurato> readAndConvertResourceData() throws Exception {

		Resource resource = resourceLoader
				.getResource("file:"+filepath);

		try (InputStream fileStream = resource.getInputStream()) {

			JAXBContext jaxbContext = JAXBContext.newInstance(Assicurati.class, Assicurato.class);
			Unmarshaller unmarhaller = jaxbContext.createUnmarshaller();
			Assicurati assicuratiPojo = (Assicurati) unmarhaller.unmarshal(fileStream);
			return assicuratiPojo.getAssicurati();

		} catch (IOException | JAXBException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void saveXml(List<AssicuratoDTO> assicuratiDTO, File file) throws JAXBException, FileNotFoundException {
		
		Assicurati assicurati = new Assicurati();

		JAXBContext jaxbContext = JAXBContext.newInstance(Assicurati.class, Assicurato.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		assicurati.getAssicurati().addAll(AssicuratoDTO.createPojoListFromDTOList(assicuratiDTO));
		marshaller.marshal(assicurati, new FileOutputStream(file));
	}

	public void deleteFileAfterProcessing(String fileToDelete) {
		File file = new File(fileToDelete);
		file.delete();
	}
	
}
