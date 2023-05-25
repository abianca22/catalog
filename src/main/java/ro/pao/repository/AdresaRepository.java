package ro.pao.repository;

import ro.pao.model.Adresa;
import ro.pao.model.ExampleClass;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdresaRepository {
    Optional<ExampleClass> getAdresaById(UUID id);

    void deleteAdresaById(UUID id);

    void updateAdresaById(UUID id, Adresa newAdresa);

    void addNewAdresa(Adresa adresa);

    List<Adresa> getAll();

    void addAllFromGivenList(List<Adresa> listaAdrese);
}
