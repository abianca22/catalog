package ro.pao.service;

import lombok.ToString;
import ro.pao.model.Scoala;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface ScoalaService {
    //Dupa denumire sau adresa
    public Optional<Scoala> getByAtribut(Object atribut);

    public List<Scoala> listeazaLista();

    public void adaugaScoala(Scoala scoala);

    public void adaugaScoli(List<Scoala> scoli);

    public void stergeScoala(Scoala scoala);

    public void modificaScoala(Scoala deModificat, Scoala inlocuitoare);

    public void sorteazaNume();
}
