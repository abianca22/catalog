package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.pao.model.Catalog;
import ro.pao.repository.CatalogRepository;
import ro.pao.service.CatalogService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;

    @Override
    public Optional<Catalog> getById(UUID id){
        return catalogRepository.getCatalogById(id);
    }

    @Override
    public void deleteById(UUID id){
        catalogRepository.deleteCatalogById(id);
    }

    @Override
    public void updateClasaById(UUID id, Catalog catalog){
        catalogRepository.updateClasaById(id, catalog);
    }

    @Override
    public void add(Catalog catalog){
        catalogRepository.addNewCatalog(catalog);
    }

    @Override
    public List<Catalog> getAll(){
        return catalogRepository.getAll();
    }
}
