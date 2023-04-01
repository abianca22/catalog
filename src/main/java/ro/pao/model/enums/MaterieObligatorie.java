package ro.pao.model.enums;

import lombok.Setter;

public enum MaterieObligatorie {

    MATE(4, "Matematica"),
    ROMANA(4, "Limba si Literatura Romana"),
    FIZICA(1, "fizica"),
    CHIMIE(1, "Chimie"),
    ENGLEZA(1, "Limba engleza"),
    GEOGRAFIE(1, "Geografie"),
    ISTORIE(1, "Istorie"),
    DIRIGENTIE(1, "Dirigentie"),
    MUZICA(1, "Educatie muzicala"),
    DESEN(1, "Desen"),
    SPORT(1, "Educatie fizica si sport");


    private Integer nrOre;
    private final String denumire;
    MaterieObligatorie(Integer ore, String nume){
        this.nrOre = ore;
        this.denumire = nume;
    }

    public Integer getNrOre(){
        return this.nrOre;
    }
    public String getDenumire(){
        return this.denumire;
    }

    public void setNrOre(Integer ore){
        this.nrOre = ore;
    }
}
