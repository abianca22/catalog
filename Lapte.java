public class Lapte extends Aliment {
    public Lapte(Long dataExp, String ingrediente, Double pret, Double calorii, Categorie.Categorii categorie)
    {
        this.dataExp = dataExp;
        this.ingrediente = ingrediente;
        this.pret = pret;
        this.calorii = calorii;
        this.categorie = categorie;
    }
}
