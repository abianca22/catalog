package ro.pao.service;

import ro.pao.model.Elev;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ElevService {
    Optional<Elev> getById(UUID id);

    List<Elev> getAll();

    void deleteById(UUID id);

    void updateNumeById(UUID id, Elev elev);

    void updatePrenumeById(UUID id, Elev elev);

    void add(Elev elev);

    void addAllFromGivenList(List<Elev> elevi);

}
