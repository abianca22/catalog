package ro.pao.repository;

import ro.pao.exceptions.IdNotFound;
import ro.pao.exceptions.NoObject;
import ro.pao.model.Adresa;
import ro.pao.model.ExampleClass;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdresaRepository {
    Optional<Adresa> getAdresaById(UUID id);

    void deleteAdresaById(UUID id);

    void updateStradaById(UUID id, Adresa newAdresa);

    void updateNumarById(UUID id, Adresa newAdresa);

    void addNewAdresa(Adresa adresa);

    List<Adresa> getAll() throws NoObject;

    void addAllFromGivenList(List<Adresa> listaAdrese);
}
