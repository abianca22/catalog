package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Adresa {
    private String judet;
    private String localitate;
    private String strada;
    private Integer numar;
    private Optional<Integer> codPostal;
    private Optional<String> tara;
}
