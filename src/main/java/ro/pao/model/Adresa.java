package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Optional;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Adresa {
    private UUID id;
    private String judet;
    private String localitate;
    private String strada;
    private Integer numar;
    private Integer codPostal;
    private String tara;
}
