package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.abstracts.Persoana;
import ro.pao.model.enums.MaterieObligatorie;
import ro.pao.model.enums.MaterieOptionala;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class Profesor extends Persoana {
        private ArrayList<MaterieObligatorie> disciplineObligatorii;
        private ArrayList<MaterieOptionala> disciplineOptionale;
        private Optional<String> facultate;


        Profesor(){
                super();
                disciplineObligatorii = new ArrayList<MaterieObligatorie>();
                disciplineOptionale = new ArrayList<MaterieOptionala>();
        }

}
