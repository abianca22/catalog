package ro.pao.repository;

import ro.pao.model.Catalog;
import ro.pao.model.Elev;

import javax.xml.catalog.CatalogFeatures;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogRepository {
    Optional<Catalog> getCatalogById(UUID id);

    void deleteCatalogById(UUID id);

    void updateClasaById(UUID id, Catalog newCatalog);

    void addNewCatalog(Catalog catalog);

    List<Catalog> getAll();
}
