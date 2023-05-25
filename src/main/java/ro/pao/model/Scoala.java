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
    private Adresa adresa;

    public Scoala(){
        adresa = new Adresa();
    }
}
