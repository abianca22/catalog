package ro.pao.repository;

import ro.pao.model.ExampleClass;
import ro.pao.model.Semestru;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SemestruRepository {
    Optional<Semestru> getSemestruById(UUID id);

    void deleteSemestruById(UUID id);

    void updateSemestruById(UUID id, Semestru newSemestru);

    void addNewSemestru(Semestru semestru);

    List<Semestru> getAll();

    void addAllFromGivenList(List<Semestru> semestre);
}
