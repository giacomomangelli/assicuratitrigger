package it.prova.assicuratitrigger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.assicuratitrigger.model.Assicurato;
import it.prova.assicuratitrigger.repository.AssicuratoRepository;

@Service
public class AssicuratoServiceImpl implements AssicuratoService {

	@Autowired
	private AssicuratoRepository repository;

	@Transactional(readOnly = true)
	public List<Assicurato> listAllAssicurati() {
		return (List<Assicurato>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Assicurato caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	public Assicurato aggiorna(Assicurato assicuratoInstance) {
		return repository.save(assicuratoInstance);
	}

	@Transactional
	public Assicurato inserisci(Assicurato assicuratoInstance) {
		return repository.save(assicuratoInstance);
	}

	@Transactional
	public void rimuovi(Assicurato assicuratoInstance) {
		repository.delete(assicuratoInstance);
	}

	@Transactional
	public void inserisciOAggiorna(List<Assicurato> assicurati) {
		if (assicurati == null || assicurati.isEmpty()) {
			throw new RuntimeException();
		}
		for (Assicurato assicuratoItem : assicurati) {
			Assicurato assicuratoLoaded = repository.findByCodiceFiscale(assicuratoItem.getCodiceFiscale());
			if (assicuratoLoaded == null) {
				repository.save(assicuratoItem);
			} else {
				assicuratoLoaded
						.setNuoviSinistri(assicuratoLoaded.getNuoviSinistri() + assicuratoItem.getNuoviSinistri());
				repository.save(assicuratoLoaded);
			}
		}

	}

}
