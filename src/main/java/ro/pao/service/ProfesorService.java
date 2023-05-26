package ro.pao.service;

import ro.pao.model.Profesor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfesorService {
    Optional<Profesor> getById(UUID id);

    void deleteById(UUID id);

    void updateById(UUID id, Profesor prof);

    void add(Profesor profesor);

    List<Profesor> getAll();

    void addAllFromGivenList(List<Profesor> profesori);
}
