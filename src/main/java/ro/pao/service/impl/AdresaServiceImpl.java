package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.pao.model.Adresa;
import ro.pao.repository.AdresaRepository;
import ro.pao.service.AdresaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class AdresaServiceImpl implements AdresaService {

    private final AdresaRepository adresaRepository;

    @Override
    public Optional<Adresa> getById(UUID id){
        return adresaRepository.getAdresaById(id);
    }

    @Override
    public void deleteById(UUID id){
        adresaRepository.deleteAdresaById(id);
    }

    @Override
    public void updateStradaById(UUID id, Adresa adresa){
        adresaRepository.updateStradaById(id, adresa);
    }

    @Override
    public void updateNumarById(UUID id, Adresa adresa){
        adresaRepository.updateNumarById(id, adresa);
    }

    @Override
    public void add(Adresa adresa){
        adresaRepository.addNewAdresa(adresa);
    }

    @Override
    public List<Adresa> getAll(){
        return adresaRepository.getAll();
    }

    @Override
    public void addFromGivenList(List<Adresa> listaAdrese){
        adresaRepository.addAllFromGivenList(listaAdrese);
    }
}
