package ro.pao.service;

import ro.pao.model.Catalog;
import ro.pao.model.Elev;
import ro.pao.model.Nota;
import ro.pao.model.Semestru;
import ro.pao.model.enums.MaterieObligatorie;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface SemestruService {
    public Map<Elev, Integer> numaraAbsente(Semestru semestru);

    public void adaugaElev(Catalog catalog, Elev elev);

    public void stergeElev(Catalog catalog, Elev elev);

    public void adaugaNotaObl(Catalog catalog, Integer semestru, MaterieObligatorie materie, Nota nota, Elev elev);



    }
