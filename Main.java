import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
            Carne vita = new Carne(23042024L, "carne de vita", 23.0, 50.0, Categorie.Categorii.MEZELURI);
            Carne porc = new Carne(27082023L, "carne de porc", 25.0, 70.0, Categorie.Categorii.MEZELURI);
            Lapte zuzu = new Lapte(23032023L, "lapte de vaca", 7.0, 25.0, Categorie.Categorii.LACTATE);
            Lapte napolact = new Lapte(21042023L, "lapte de vaca", 8.0, 29.7, Categorie.Categorii.LACTATE);
            Cereale cereale1 = new Cereale(25052025L, "porumb, grau, orz", 15.0, 35.0, Categorie.Categorii.PAINE, Cereale.TipCereale.NESQUIK);
            Cereale cereale2 = new Cereale(17062024L, "porumb, orz, ovaz", 14.0, 28.9, Categorie.Categorii.PAINE, Cereale.TipCereale.CHOCAPIC);

            List<Aliment> alimente = List.of(vita, porc, zuzu, napolact, cereale1, cereale2);
            List<Aliment> alimente_sortate = alimente.stream().sorted((a1, a2) -> a1.getCalorii().compareTo(a2.getCalorii())).toList();

        for(Aliment a: alimente)
        {
            if (!(a instanceof Cereale))
                System.out.println(a.getDataExp() + ", " + a.getIngrediente() + ", " + a.getPret() + ", " + a.getCalorii() + ", " + a.getCategorie());
            else
                System.out.println(a.getDataExp() + ", " + a.getIngrediente() + ", " + a.getPret() + ", " + a.getCalorii() + ", " + a.getCategorie() + ", " + ((Cereale) a).getTipCereale());

        }
    }

}