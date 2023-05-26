package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.abstracts.Persoana;
import ro.pao.model.enums.Invatare;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Elev extends Persoana implements Comparable<Elev>{
    private UUID nrMatricol;

    private Invatare stilInvatare;

    private Adresa adresa;

    @Override
    public int compareTo(Elev o) {
        if (this.nume.compareTo(o.nume) == 0){
            return this.prenume.compareTo(o.prenume);
        }
        return this.nume.compareTo(o.nume);
    }

    public Elev(Rol rol){
        super(rol);
        this.nume = "";
        this.prenume = "";
        this.cnp = "";
        this.dataNastere = null;
        this.nrMatricol = UUID.randomUUID();
        this.stilInvatare = Invatare.NONE;
        this.adresa = new Adresa();
    }

    public Elev(){
        super();
        this.adresa = new Adresa();
        this.nrMatricol = UUID.randomUUID();
    }

    public void printRole(){
        System.out.println(rol.afiseazaRol() + " " + this.toString());
    }


}
