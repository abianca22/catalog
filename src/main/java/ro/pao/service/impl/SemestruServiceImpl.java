package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.pao.model.Semestru;
import ro.pao.repository.SemestruRepository;
import ro.pao.service.SemestruService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class SemestruServiceImpl implements SemestruService{
    private final SemestruRepository semestruRepository;

    @Override
    public Optional<Semestru> getById(UUID id){
        return semestruRepository.getSemestruById(id);
    }

    @Override
    public List<Semestru> getAllFromList(){
        return semestruRepository.getAll();
    }

    @Override
    public void add(Semestru semestru){
        semestruRepository.addNewSemestru(semestru);
    }

    @Override
    public void deleteById(UUID id){
        semestruRepository.deleteSemestruById(id);
    }

    @Override
    public void updateById(UUID id, Semestru semestru){
        semestruRepository.updateSemestruById(id, semestru);
    }

    @Override
    public void addAllFromGivenList(List<Semestru> semestre){
        semestruRepository.addAllFromGivenList(semestre);
    }
}