package ro.pao.service.impl;

import lombok.NoArgsConstructor;
import ro.pao.model.Catalog;
import ro.pao.model.Elev;
import ro.pao.model.Nota;
import ro.pao.model.Semestru;
import ro.pao.model.enums.MaterieObligatorie;
import ro.pao.model.enums.MaterieOptionala;
import ro.pao.service.SemestruService;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SemestruServiceImpl implements SemestruService {
    @Override
    public Map<Elev, Integer> numaraAbsente(Semestru semestru){
        Map<Elev, Integer> nrAbsente = new HashMap<>();
        if (semestru.getAbsenteObligatorii() != null && !semestru.getAbsenteObligatorii().isEmpty()){

            for(Map.Entry<Elev, Map<MaterieObligatorie, ArrayList<LocalDate>>> entry: semestru.getAbsenteObligatorii().entrySet()){
                Integer nrAbs = 0;
                for(Map.Entry<MaterieObligatorie, ArrayList<LocalDate>> entry2: entry.getValue().entrySet()){
                    nrAbs += entry2.getValue().size();
                }
                nrAbsente.put(entry.getKey(), nrAbs);
            }

        }
        if (semestru.getAbsenteOptionale() != null && !semestru.getAbsenteOptionale().isEmpty()){
        for(Map.Entry<Elev, Map<MaterieOptionala, ArrayList<LocalDate>>> entry: semestru.getAbsenteOptionale().entrySet()){
            Integer nrAbs = 0;
            for(Map.Entry<MaterieOptionala, ArrayList<LocalDate>> entry2: entry.getValue().entrySet()){
                nrAbs += entry2.getValue().size();
            }
            nrAbsente.put(entry.getKey(), nrAbsente.get(entry.getKey()) + nrAbs);
        }
        }

        return nrAbsente;
    }

    public void adaugaElev(Catalog catalog, Elev elev){

        Long len = catalog.getElevi()
                .entrySet()
                .stream()
                .filter(p -> p.getValue().equals(elev))
                .count();
        if(len > 0)
            return;

        Map<MaterieObligatorie, ArrayList<Nota>> aux = new TreeMap<>();
        for(int i = 0; catalog.getMateriiObligatorii() != null && i < catalog.getMateriiObligatorii().size(); i++) {
            aux.put(catalog.getMateriiObligatorii().get(i), new ArrayList<Nota>());
        }
        Map<MaterieOptionala, ArrayList<Nota>> aux2 = new TreeMap<>();
        for(int i = 0; catalog.getMateriiOptionale() != null && i < catalog.getMateriiOptionale().size(); i++){
            aux2.put(catalog.getMateriiOptionale().get(i), new ArrayList<Nota>());
        }
        Map<MaterieObligatorie, ArrayList<LocalDate>> aux3 = new TreeMap<>();
        for(int i = 0; catalog.getMateriiObligatorii() != null && i < catalog.getMateriiObligatorii().size(); i++){
            aux3.put(catalog.getMateriiObligatorii().get(i), new ArrayList<LocalDate>());
        }
        Map<MaterieOptionala, ArrayList<LocalDate>> aux4 = new TreeMap<>();
        for(int i = 0; catalog.getMateriiOptionale() != null && i < catalog.getMateriiOptionale().size(); i++){
            aux4.put(catalog.getMateriiOptionale().get(i), new ArrayList<LocalDate>());
        }
        Map<MaterieObligatorie, Integer> aux5 = new TreeMap<>();
        Map<Elev, Map<MaterieObligatorie, ArrayList<LocalDate>>> absObl = catalog.getSemestru1().getAbsenteObligatorii();
        Map<Elev, Map<MaterieOptionala, ArrayList<LocalDate>>> absOpt = catalog.getSemestru1().getAbsenteOptionale();
        Map<Elev, Map<MaterieObligatorie, ArrayList<Nota>>> matObl = catalog.getSemestru1().getSituatieObligatorii();
        Map<Elev, Map<MaterieOptionala, ArrayList<Nota>>> matOpt = catalog.getSemestru1().getSituatieOptionale();
        Map<Elev, Map<MaterieObligatorie, Integer>> teze = catalog.getSemestru1().getTeze();
        matObl.put(elev, aux);
        matOpt.put(elev, aux2);
        absObl.put(elev, aux3);
        absOpt.put(elev, aux4);
        teze.put(elev, new TreeMap<>());
        Semestru sem = catalog.getSemestru1();
        sem.setAbsenteObligatorii(absObl);
        sem.setAbsenteOptionale(absOpt);
        sem.setSituatieObligatorii(matObl);
        sem.setSituatieOptionale(matOpt);
        sem.setTeze(teze);
        catalog.setSemestru1(sem);
        Semestru sem2 = catalog.getSemestru2();
        sem2.setAbsenteObligatorii(absObl);
        sem2.setAbsenteOptionale(absOpt);
        sem2.setSituatieObligatorii(matObl);
        sem2.setSituatieOptionale(matOpt);
        sem2.setTeze(teze);
        catalog.setSemestru2(sem2);
        Map<UUID, Elev> elevi = catalog.getElevi();
         elevi.put(elev.getNrMatricol(), elev);
         catalog.setElevi(elevi);

    }

    public void stergeElev(Catalog catalog, Elev elev){
        Map<UUID, Elev> elevi = catalog.getElevi();
        elevi = elevi.entrySet()
                .stream()
                .filter(p -> !p.getValue().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        catalog.setElevi(elevi);
        Map<Elev, Map<MaterieObligatorie, ArrayList<Nota>>> aux = catalog.getSemestru1().getSituatieObligatorii();
        aux = aux.entrySet()
                .stream()
                .filter(o -> !o.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        Map<Elev, Map<MaterieOptionala, ArrayList<Nota>>> aux2 = catalog.getSemestru1().getSituatieOptionale();
        aux2 = aux2.entrySet()
                .stream()
                .filter(o -> !o.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        Map<Elev, Map<MaterieObligatorie, ArrayList<LocalDate>>> aux3 = catalog.getSemestru1().getAbsenteObligatorii();
        aux3 = aux3.entrySet()
                .stream()
                .filter(o -> !o.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        Map<Elev, Map<MaterieOptionala, ArrayList<LocalDate>>> aux4 = catalog.getSemestru1().getAbsenteOptionale();
        aux4 = aux4.entrySet()
                .stream()
                .filter(o -> !o.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        Map<Elev, Map<MaterieObligatorie, Integer>> teze = catalog.getSemestru1().getTeze();
        teze = teze.entrySet()
                .stream()
                .filter(p -> !p.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        Semestru s1 = catalog.getSemestru1();
        s1.setSituatieObligatorii(aux);
        s1.setSituatieOptionale(aux2);
        s1.setAbsenteObligatorii(aux3);
        s1.setAbsenteOptionale(aux4);
        s1.setTeze(teze);
        catalog.setSemestru1(s1);


        aux = catalog.getSemestru2().getSituatieObligatorii();
        aux = aux.entrySet()
                .stream()
                .filter(o -> !o.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        aux2 = catalog.getSemestru2().getSituatieOptionale();
        aux2 = aux2.entrySet()
                .stream()
                .filter(o -> !o.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        aux3 = catalog.getSemestru2().getAbsenteObligatorii();
        aux3 = aux3.entrySet()
                .stream()
                .filter(o -> !o.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        aux4 = catalog.getSemestru2().getAbsenteOptionale();
        aux4 = aux4.entrySet()
                .stream()
                .filter(o -> !o.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        teze = catalog.getSemestru2().getTeze();
        teze = teze.entrySet()
                .stream()
                .filter(p -> !p.getKey().equals(elev))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        s1 = catalog.getSemestru2();
        s1.setSituatieObligatorii(aux);
        s1.setSituatieOptionale(aux2);
        s1.setAbsenteObligatorii(aux3);
        s1.setAbsenteOptionale(aux4);
        s1.setTeze(teze);
        catalog.setSemestru2(s1);
    }

    @Override
    public void adaugaNotaObl(Catalog catalog, Integer semestru, MaterieObligatorie materie, Nota nota, Elev elev){
        switch(semestru){
            case 1:
                Map<Elev, Map<MaterieObligatorie, ArrayList<Nota>>> elevi = catalog.getSemestru1().getSituatieObligatorii();
                Map<MaterieObligatorie, ArrayList<Nota>> materii = elevi.get(elev);
                ArrayList<Nota> lista = materii.get(materie);
                lista.add(nota);
                materii.put(materie, lista);
                elevi.put(elev, materii);
                Semestru sem = catalog.getSemestru1();
                sem.setSituatieObligatorii(elevi);
                catalog.setSemestru1(sem);
                break;
            case 2:
                elevi = catalog.getSemestru2().getSituatieObligatorii();
                materii = elevi.get(elev);
                lista = materii.get(materie);
                lista.add(nota);
                materii.put(materie, lista);
                elevi.put(elev, materii);
                sem = catalog.getSemestru2();
                sem.setSituatieObligatorii(elevi);
                catalog.setSemestru2(sem);
                break;
            default:
                return;
        }
    }
}
