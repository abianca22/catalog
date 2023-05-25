package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.enums.MaterieObligatorie;
import ro.pao.model.enums.MaterieOptionala;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@SuperBuilder
public class Semestru {

    private UUID id;
    private Map<Elev, Double> medieSemestriala;

//    private Map<Elev, Map<MaterieObligatorie, ArrayList<Nota>>> situatieObligatorii;
//    private Map<Elev, Map<MaterieOptionala, ArrayList<Nota>>> situatieOptionale;
//    private Map<Elev, Map<MaterieObligatorie, ArrayList<LocalDate>>> absenteObligatorii;
//    private Map<Elev, Map<MaterieOptionala, ArrayList<LocalDate>>> absenteOptionale;
//    private Map<Elev, Map<MaterieObligatorie, Integer>> teze;

    public Semestru(){
        this.medieSemestriala = new HashMap<>();
    }

}
