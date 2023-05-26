package ro.pao.repository;

import ro.pao.model.ExampleClass;
import ro.pao.model.Profesor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfesorRepository {
    Optional<Profesor> getProfById(UUID id);

    void deleteProfById(UUID id);

    void updateProfById(UUID id, Profesor newProf);

    void addNewProf(Profesor prof);

    List<Profesor> getAll();

    void addAllFromGivenList(List<Profesor> profesori);
}
