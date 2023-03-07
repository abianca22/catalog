public class Cereale extends Aliment{

    enum TipCereale{
        NESQUIK, CHOCAPIC
    }
    private TipCereale tipCereale;

    public Cereale(Long dataExp, String ingrediente, Double pret, Double calorii, Categorie.Categorii categorie, TipCereale tipCereale)
    {
        this.dataExp = dataExp;
        this.ingrediente = ingrediente;
        this.pret = pret;
        this.calorii = calorii;
        this.categorie = categorie;
        this.tipCereale = tipCereale;
    }

    public TipCereale getTipCereale() {
        return tipCereale;
    }

    public void setTipCereale(TipCereale tipCereale) {
        this.tipCereale = tipCereale;
    }
}
