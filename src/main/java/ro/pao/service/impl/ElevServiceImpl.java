package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.pao.model.Elev;
import ro.pao.repository.ElevRepository;
import ro.pao.service.ElevService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class ElevServiceImpl implements ElevService {

    private final ElevRepository elevRepository;

    @Override
    public Optional<Elev> getById(UUID id){
        return elevRepository.getElevById(id);
    }

    @Override
    public List<Elev> getAll(){
        return elevRepository.getAll();
    }

    @Override
    public void deleteById(UUID id){
        elevRepository.deleteElevById(id);
    }

    @Override
    public void updateNumeById(UUID id, Elev elev){
        elevRepository.updateNumeById(id, elev);
    }

    @Override
    public void updatePrenumeById(UUID id, Elev elev){
        elevRepository.updatePrenumeById(id, elev);
    }

    @Override
    public void add(Elev elev){
        elevRepository.addNewElev(elev);
    }

    @Override
    public void addAllFromGivenList(List<Elev> elevi) {
        elevRepository.addAllFromGivenList(elevi);
    }
}
