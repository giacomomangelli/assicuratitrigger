package it.prova.assicuratitrigger.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assicurato")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assicurato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "{nome.notblank}")
	@Column(name = "nome")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	@Column(name = "cognome")
	private String cognome;

	@NotNull(message = "{dataNascita.notnull}")
	@Column(name = "data_nascita")
	private Date dataNascita;

	@NotNull(message = "{nuoviSinistri.notnull}")
	@Min(0)
	@Column(name = "nuovi_sinistri")
	private Integer nuoviSinistri;

	@NotBlank(message = "{codiceFiscale.notblank}")
	@Column(name = "codice_fiscale")
	private String codiceFiscale;

}
