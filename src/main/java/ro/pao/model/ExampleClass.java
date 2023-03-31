package ro.pao.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import ro.pao.model.abstracts.AbstractEntity;
import ro.pao.model.enums.EnumExample;


@SuperBuilder
@Getter
public class ExampleClass extends AbstractEntity {

    private EnumExample enumExample;
}
