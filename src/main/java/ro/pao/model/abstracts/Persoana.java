package ro.pao.model.abstracts;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.Rol;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@ToString
public abstract class Persoana {

    protected Rol rol;

    protected String nume;
    protected String prenume;
    protected String cnp;
    protected LocalDate dataNastere;

    public Persoana(Rol rol){
        this.rol = rol;
    }
}
