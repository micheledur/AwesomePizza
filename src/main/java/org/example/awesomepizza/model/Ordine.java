package org.example.awesomepizza.model;



import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codiceOrdine;

    @Enumerated(EnumType.STRING)
    private StatoOrdine statoOrdine;

    private String dettagliPizza;

    private LocalDateTime timestampOrdine;

    @PrePersist
    protected void onCreate() {
        timestampOrdine = LocalDateTime.now();
        codiceOrdine = "ORD-" + UUID.randomUUID().toString();
    }
}