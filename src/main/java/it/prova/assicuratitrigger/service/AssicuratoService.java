package it.prova.assicuratitrigger.service;

import java.util.List;

import it.prova.assicuratitrigger.model.Assicurato;

public interface AssicuratoService {
	
	public List<Assicurato> listAllAssicurati();

	public Assicurato caricaSingoloElemento(Long id);

	public Assicurato aggiorna(Assicurato assicuratoInstance);

	public Assicurato inserisci(Assicurato assicuratoInstance);

	public void rimuovi(Assicurato assicuratoInstance);
	
	public void inserisciOAggiorna(List<Assicurato> assicurati);
	
}
