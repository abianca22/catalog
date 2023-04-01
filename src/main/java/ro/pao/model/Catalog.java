package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
public class Catalog {
    private Scoala scoala;
    private Integer clasa;
    private Character literaClasa;
    private Semestru semestru1;
    private Semestru semestru2;
    private Double medieAnuala;
    private ArrayList<Profesor> profesoriClasa;
    private Optional<String> profil;
    private ArrayList<Elev> elevi;

    public Catalog(){
        this.scoala = new Scoala();
        this.semestru1 = new Semestru();
        this.semestru2 = new Semestru();
        this.medieAnuala = 0d;
        this.profesoriClasa = new ArrayList<>();
    }

}
