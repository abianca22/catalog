package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.enums.MaterieObligatorie;
import ro.pao.model.enums.MaterieOptionala;

import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
public class Catalog {

    private UUID id;
    private Scoala scoala;
    private Integer clasa;
    private Character literaClasa;
    private Semestru semestru1;
    private Semestru semestru2;
    //private ArrayList<Profesor> profesoriClasa;
    private Map<UUID, Elev> elevi;
    //private ArrayList<MaterieObligatorie> materiiObligatorii;
    //private ArrayList<MaterieOptionala> materiiOptionale;
    // private HashMap<Elev, Double> mediiAnuale;
    //private Optional<String> profil;


    public Catalog(){
        this.scoala = new Scoala();
        this.semestru1 = new Semestru();
        this.semestru2 = new Semestru();
       // this.materiiObligatorii = new ArrayList<>();
       // this.materiiOptionale = new ArrayList<>();
        this.elevi = new HashMap<>();
    }

}
