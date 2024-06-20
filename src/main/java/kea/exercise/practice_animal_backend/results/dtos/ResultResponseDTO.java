package kea.exercise.practice_animal_backend.results.dtos;

import kea.exercise.practice_animal_backend.results.ResultType;

import java.time.LocalDate;

public record ResultResponseDTO (int id, String result, LocalDate date, ResultType resultType, String discipline) {
}
