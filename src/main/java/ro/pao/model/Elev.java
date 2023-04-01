package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.abstracts.Persoana;
import ro.pao.model.enums.Invatare;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


@Getter
@SuperBuilder
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Elev extends Persoana implements Comparable<Elev>{
    private UUID nrMatricol;

    @Setter
    private Invatare stilInvatare;

    @Setter
    private Adresa adresa;

    @Override
    public int compareTo(Elev o) {
        if (this.nume.compareTo(o.nume) == 0){
            return this.prenume.compareTo(o.prenume);
        }
        return this.nume.compareTo(o.nume);
    }

    public Elev(){
        super();
        this.nume = "";
        this.prenume = "";
        this.nrMatricol = UUID.randomUUID();
        this.stilInvatare = Invatare.NONE;
        this.adresa = new Adresa();
    }

}
