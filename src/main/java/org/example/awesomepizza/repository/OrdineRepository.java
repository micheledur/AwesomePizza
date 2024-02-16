package org.example.awesomepizza.repository;

import org.example.awesomepizza.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    Ordine findByCodiceOrdine(String codiceOrdine);

}
