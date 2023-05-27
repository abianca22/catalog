package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.pao.application.csv.CsvWriter;
import ro.pao.exceptions.IdNotFound;
import ro.pao.exceptions.NoObject;
import ro.pao.model.Adresa;
import ro.pao.model.ExampleClass;
import ro.pao.repository.AdresaRepository;
import ro.pao.service.AdresaService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class AdresaServiceImpl implements AdresaService {

    private final AdresaRepository adresaRepository;

    @Override
    public Optional<Adresa> getById(UUID id) throws IdNotFound {
        return adresaRepository.getAdresaById(id);
    }

    @Override
    public void deleteById(UUID id) throws IdNotFound {
        adresaRepository.deleteAdresaById(id);
    }

    @Override
    public void updateStradaById(UUID id, Adresa adresa) throws IdNotFound{
        adresaRepository.updateStradaById(id, adresa);
    }

    @Override
    public void updateNumarById(UUID id, Adresa adresa) throws IdNotFound{
        adresaRepository.updateNumarById(id, adresa);
    }

    @Override
    public void add(Adresa adresa){
        adresaRepository.addNewAdresa(adresa);
    }

    @Override
    public List<Adresa> getAll() throws NoObject {
        return adresaRepository.getAll();
    }

    @Override
    public void addFromGivenList(List<Adresa> listaAdrese){
        adresaRepository.addAllFromGivenList(listaAdrese);
    }

}
