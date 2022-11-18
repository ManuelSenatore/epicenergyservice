package com.buildweek.epicenergyservice.entities;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClienteConverter {

    private int indirizzoLegaleId;
    private int indirizzoOperativoId;
    private String email;
    private String pec;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private int telefonoContatto;
    private RagioneSociale ragioneSociale;
    private int fatturatoAnnuo;
}