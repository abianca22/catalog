package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.pao.model.Profesor;
import ro.pao.repository.ProfesorRepository;
import ro.pao.service.ProfesorService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class ProfesorServiceImpl implements ProfesorService {
    private final ProfesorRepository profesorRepository;

    @Override
    public Optional<Profesor> getById(UUID id){
        return profesorRepository.getProfById(id);
    }

    @Override
    public void deleteById(UUID id){
        profesorRepository.deleteProfById(id);
    }

    @Override
    public void updateById(UUID id, Profesor prof){
        profesorRepository.updateProfById(id, prof);
    }

    @Override
    public void add(Profesor prof){
        profesorRepository.addNewProf(prof);
    }

    @Override
    public List<Profesor> getAll(){
        return profesorRepository.getAll();
    }
}
