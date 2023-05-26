package ro.pao.service;

import ro.pao.model.Catalog;
import ro.pao.model.Elev;
import ro.pao.model.Nota;
import ro.pao.model.Semestru;
import ro.pao.model.enums.MaterieObligatorie;

import java.util.*;

public interface SemestruService {
    public Optional<Semestru> getById(UUID id);

    public List<Semestru> getAllFromList();

    public void add(Semestru semestru);

    public void deleteById(UUID id);

    public void updateById(UUID id, Semestru semestru);

    public void addAllFromGivenList(List<Semestru> semestre);
}
