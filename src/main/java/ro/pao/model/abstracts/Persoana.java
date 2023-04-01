package ro.pao.model.abstracts;

import lombok.*;
import lombok.experimental.SuperBuilder;

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
    protected String nume;
    protected String prenume;
    protected String cnp;
    protected LocalDate dataNastere;
}
