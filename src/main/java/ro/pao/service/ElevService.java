package ro.pao.service;

import ro.pao.exceptions.IdNotFound;
import ro.pao.model.Elev;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ElevService {
    Optional<Elev> getById(UUID id) throws IdNotFound;

    List<Elev> getAll();

    void deleteById(UUID id) throws IdNotFound;

    void updateNumeById(UUID id, Elev elev) throws IdNotFound;

    void updatePrenumeById(UUID id, Elev elev) throws IdNotFound;

    void add(Elev elev);

    void addAllFromGivenList(List<Elev> elevi);

}
