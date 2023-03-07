public class Aliment {
    protected Long dataExp;
    protected String ingrediente;
    protected Double pret;
    protected Double calorii;
    protected Categorie.Categorii categorie;


    public Long getDataExp() {
        return dataExp;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public Double getPret() {
        return pret;
    }

    public Double getCalorii() {
        return calorii;
    }

    public void setDataExp(Long dataExp) {
        this.dataExp = dataExp;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public void setCalorii(Double calorii) {
        this.calorii = calorii;
    }

    public Categorie.Categorii getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie.Categorii categorie) {
        this.categorie = categorie;
    }
}
