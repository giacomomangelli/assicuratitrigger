package it.prova.assicuratitrigger.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.assicuratitrigger.model.Assicurato;

public interface AssicuratoRepository extends CrudRepository<Assicurato, Long> {

	Assicurato findByCodiceFiscale(String codiceFiscale);
	
}
