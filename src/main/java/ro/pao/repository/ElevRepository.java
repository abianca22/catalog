package ro.pao.repository;

import ro.pao.model.Elev;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ElevRepository {
    // dupa numarul matricol
    Optional<Elev> getElevById(UUID id);

    void deleteElevById(UUID id);

    void updateNumeById(UUID id, Elev elevNou);

    void updatePrenumeById(UUID id, Elev elevNou);

    void addNewElev(Elev elev);

    List<Elev> getAll();

    void addAllFromGivenList(List<Elev> elevi);
}
