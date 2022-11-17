package com.buildweek.epicenergyservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fatture")
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long numero;
    private int anno;
    private double importo;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private StatoFattura stato;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "cliente_id")
    private Client cliente;

}
