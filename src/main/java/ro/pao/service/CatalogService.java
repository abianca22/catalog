package ro.pao.service;

import ro.pao.model.Catalog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogService {
    Optional<Catalog> getById(UUID id);

    void deleteById(UUID id);

    void updateClasaById(UUID id, Catalog catalog);

    void add(Catalog catalog);

    List<Catalog> getAll();
}
