package org.example.awesomepizza.service;

import org.example.awesomepizza.model.Ordine;
import org.example.awesomepizza.model.StatoOrdine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.awesomepizza.repository.OrdineRepository;

import java.util.Arrays;
import java.util.List;


@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    public Ordine creaOrdine(String dettagliPizza) {
        Ordine ordine = new Ordine();
        ordine.setDettagliPizza(dettagliPizza);
        ordine.setStatoOrdine(StatoOrdine.IN_ATTESA);
        return ordineRepository.save(ordine);
    }

    public Ordine aggiornaStatoOrdine(Long id, String statoOrdine) {
        Ordine ordine = ordineRepository.findById(id).orElseThrow(() -> new RuntimeException("Ordine non trovato"));
        StatoOrdine statoOrdineEnum = Arrays.stream(StatoOrdine.values())
                .filter(s -> s.name().equals(statoOrdine.trim())).findFirst()
                .orElseThrow(() -> new RuntimeException("Stato ordine non trovato"));
        ordine.setStatoOrdine(statoOrdineEnum);
        return ordineRepository.save(ordine);
    }

    public List<Ordine> getCodaOrdini() {
        return ordineRepository.findAll();
    }

    public Ordine getOrdineByCodice(String codiceOrdine) {
        return ordineRepository.findByCodiceOrdine(codiceOrdine);
    }

}