package it.prova.assicuratitrigger.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.prova.assicuratitrigger.flusso.marshall.Assicurato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssicuratoDTO {

	private Long id;
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	@NotNull(message = "{dataNascita.notnull}")
	private Date dataNascita;
	@NotNull(message = "{nuoviSinistri.notnull}")
	@Min(0)
	private Integer nuoviSinistri;
	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;

	private static Date dataConverter(XMLGregorianCalendar dataFromPojo) {

		if (dataFromPojo == null) {
			return null;
		}
		return dataFromPojo.toGregorianCalendar().getTime();
	}

	private static Integer nuoviSinistriConverter(BigInteger nuoviSinistriPojo) {
		return nuoviSinistriPojo.intValue();
	}

	public static AssicuratoDTO createAssicuratoDTOFromPojo(Assicurato assicuratoPojo) {
		return AssicuratoDTO.builder()
				.nome(assicuratoPojo.getNome())
				.cognome(assicuratoPojo.getCognome())
				.dataNascita(dataConverter(assicuratoPojo.getDatanascita()))
				.nuoviSinistri(nuoviSinistriConverter(assicuratoPojo.getNuovisinistri()))
				.codiceFiscale(assicuratoPojo.getCodicefiscale())
				.build();
	}

	public static List<AssicuratoDTO> createAssicuratoDTOListFromPojoList(List<Assicurato> assicuratiPojos) {
		return assicuratiPojos.stream().map(assicuratoEntity -> createAssicuratoDTOFromPojo(assicuratoEntity)).collect(Collectors.toList());
	}
	
	public static List<it.prova.assicuratitrigger.model.Assicurato> createAssicuratoModelListFromDTOList(List<AssicuratoDTO> assicuratiDTOList){
		return assicuratiDTOList.stream().map(assicuratoEntity -> assicuratoEntity.buildModelFromDTO()).collect(Collectors.toList());
	}

	public AssicuratoDTO buildDTOFromModel(it.prova.assicuratitrigger.model.Assicurato assicuratoModel) {
		return new AssicuratoDTO(assicuratoModel.getId(), assicuratoModel.getNome(), assicuratoModel.getCognome(),
				assicuratoModel.getDataNascita(), assicuratoModel.getNuoviSinistri(),
				assicuratoModel.getCodiceFiscale());
	}
	
	public it.prova.assicuratitrigger.model.Assicurato buildModelFromDTO(){
		return new it.prova.assicuratitrigger.model.Assicurato(this.id, this.nome, this.cognome, this.dataNascita, this.nuoviSinistri, this.codiceFiscale);
	}
	
	public static Assicurato createPojoFromDTO(AssicuratoDTO assicuratoDTO) throws DatatypeConfigurationException {
		Assicurato assicurato = new Assicurato();
		assicurato.setNome(assicuratoDTO.getNome());
		assicurato.setCognome(assicuratoDTO.getCognome());
		GregorianCalendar tempCalendar = new GregorianCalendar();
		tempCalendar.setTime(assicuratoDTO.getDataNascita());
		assicurato.setDatanascita(DatatypeFactory.newInstance().newXMLGregorianCalendar(tempCalendar));
		assicurato.setNuovisinistri(BigInteger.valueOf(assicuratoDTO.getNuoviSinistri()));
		assicurato.setCodicefiscale(assicuratoDTO.getCodiceFiscale());

		return assicurato;
	}
	
	public static List<Assicurato> createPojoListFromDTOList(List<AssicuratoDTO> assicuratiDTO) {
		return assicuratiDTO.stream().map(assicuratoEntity -> {
			try {
				return createPojoFromDTO(assicuratoEntity);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}
	
}