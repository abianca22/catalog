package ro.pao.model.enums;


public enum MaterieOptionala {

    FRANCEZA(1, "Limba Franceza"),
    GERMANA(1, "Limba Germana"),
    RELIGIE(1, "Religie"),
    CHINEZA(1, "Limba Chineza"),
    JAPONEZA(1, "Limba Japoneza");

    private final String denumire;
    private Integer nrOre;
    MaterieOptionala(Integer ore, String nume){
        this.nrOre = ore;
        this.denumire = nume;
    }

    public Integer getNrOre() {
        return nrOre;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setNrOre(Integer nrOre) {
        this.nrOre = nrOre;
    }
}
