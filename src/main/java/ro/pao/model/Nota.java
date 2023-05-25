package ro.pao.model;

import ro.pao.model.enums.MaterieObligatorie;
import ro.pao.model.enums.MaterieOptionala;

import java.time.LocalDate;
import java.util.UUID;

public record Nota(UUID id, Integer nota, LocalDate data, MaterieObligatorie materieObligatorie, MaterieOptionala materieOptionala) {}
