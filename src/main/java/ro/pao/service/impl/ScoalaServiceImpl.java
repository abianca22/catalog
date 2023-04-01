package ro.pao.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ro.pao.model.Adresa;
import ro.pao.model.Scoala;
import ro.pao.service.ScoalaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@ToString
public class ScoalaServiceImpl implements ScoalaService {
    private static List<Scoala>  listaScoli = new ArrayList<>();

    @Override
    public Optional<Scoala> getByAtribut(Object atribut){
        if (atribut instanceof Adresa){
            return listaScoli.stream()
                    .filter(obiect -> obiect.getAdresa().equals(atribut))
                    .findFirst();
        }
        else if (atribut instanceof String){
            return listaScoli.stream()
                    .filter(obiect -> obiect.getDenumire().equals(atribut))
                    .findAny();
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public List<Scoala> listeazaLista(){
        return listaScoli;
    }

    @Override
    public void adaugaScoala(Scoala scoala){
        listaScoli.add(scoala);
    }

    @Override
    public void adaugaScoli(List<Scoala> scoli){
        listaScoli.addAll(scoli);
    }

    @Override
    public void stergeScoala(Scoala scoala){
        listaScoli = listaScoli.stream()
                .filter(obiect -> !obiect.equals(scoala))
                .collect(Collectors.toList());
    }

    @Override
    public void modificaScoala(Scoala deModificat, Scoala inlocuitoare){
        stergeScoala(deModificat);
        adaugaScoala(inlocuitoare);
    }
}
