package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.UUID;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Scoala {
    private UUID id;
    private String denumire;
    private ArrayList<Profesor> profesori;
    private Adresa adresa;

    public Scoala(){
        profesori = new ArrayList<Profesor>();
        adresa = new Adresa();
    }
}
