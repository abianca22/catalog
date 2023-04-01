package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.enums.MaterieObligatorie;
import ro.pao.model.enums.MaterieOptionala;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@SuperBuilder
public class Semestru {
    private Map<Elev, Double> medieSemestriala;

    private Map<Elev, Map<MaterieObligatorie, ArrayList<Nota>>> situatieObligatorii;
    private Map<Elev, Map<MaterieOptionala, ArrayList<Nota>>> situatieOptionale;
    private Map<Elev, Map<MaterieObligatorie, ArrayList<LocalDate>>> absenteObligatorii;
    private Map<Elev, Map<MaterieOptionala, ArrayList<LocalDate>>> absenteOptionale;
    private Map<Elev, Map<MaterieObligatorie, Integer>> teze;

    public Semestru(){
        medieSemestriala = new HashMap<>();
        situatieObligatorii = new TreeMap<>();
        situatieOptionale = new TreeMap<>();
        absenteObligatorii = new TreeMap<>();
        absenteOptionale = new TreeMap<>();
        teze = new TreeMap<>();
    }
}
