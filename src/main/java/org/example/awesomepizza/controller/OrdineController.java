package org.example.awesomepizza.controller;

import org.example.awesomepizza.model.Ordine;
import org.example.awesomepizza.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @PostMapping
    public ResponseEntity<Ordine> creaOrdine(@RequestBody Ordine ordine) {
        Ordine nuovoOrdine = ordineService.creaOrdine(ordine.getDettagliPizza());
        return ResponseEntity.ok(nuovoOrdine);
    }

    @PostMapping("/{id}/stato")
    public ResponseEntity<Ordine> aggiornaStatoOrdine(@PathVariable Long id, @RequestBody String stato) {
        Ordine ordineAggiornato = ordineService.aggiornaStatoOrdine(id, stato);
        if (ordineAggiornato != null) {
            return ResponseEntity.ok(ordineAggiornato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/coda")
    public ResponseEntity<List<Ordine>> getCodaOrdini() {
        List<Ordine> codaOrdini = ordineService.getCodaOrdini();
        return ResponseEntity.ok(codaOrdini);
    }

    @GetMapping("/codice/{codiceOrdine}")
    public ResponseEntity<Ordine> getOrdineByCodice(@PathVariable String codiceOrdine) {
        Ordine ordine = ordineService.getOrdineByCodice(codiceOrdine);
        if (ordine != null) {
            return ResponseEntity.ok(ordine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
