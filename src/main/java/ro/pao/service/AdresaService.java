package ro.pao.service;

import ro.pao.exceptions.IdNotFound;
import ro.pao.exceptions.NoObject;
import ro.pao.model.Adresa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdresaService {
    Optional<Adresa> getById(UUID id) throws IdNotFound;

    void deleteById(UUID id) throws IdNotFound;

    void updateStradaById(UUID id, Adresa adresa) throws IdNotFound;

    void updateNumarById(UUID id, Adresa adresa) throws IdNotFound;

    void add(Adresa adresa);

    List<Adresa> getAll() throws NoObject;

    void addFromGivenList(List<Adresa> listaAdrese);
}
