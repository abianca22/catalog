package ro.pao.service;

import ro.pao.model.ExampleClass;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ExampleService {

    Optional<ExampleClass> getById(UUID id);

    Optional<ExampleClass> getBySomeFieldOfClass(Object someFieldFromExampleClass);

    List<ExampleClass> getAllFromList();

    void addAllFromGivenList(List<ExampleClass> exampleClassList);

    void addOnlyOne(ExampleClass exampleClass);

    void removeElementById(UUID id);

    void modificaElementById(UUID id, ExampleClass newElement);
}
