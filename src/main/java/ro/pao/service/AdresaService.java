package ro.pao.service;

import ro.pao.model.Adresa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdresaService {
    Optional<Adresa> getById(UUID id);

    void deleteById(UUID id);

    void updateStradaById(UUID id, Adresa adresa);

    void updateNumarById(UUID id, Adresa adresa);

    void add(Adresa adresa);

    List<Adresa> getAll();

    void addFromGivenList(List<Adresa> listaAdrese);
}
