package kea.exercise.practice_animal_backend.results.dtos;

import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.participants.Participant;

import java.time.LocalDate;

public record ResultResponseDetailedDTO (int id, Participant participant, LocalDate date, double resultValue, String result, Discipline discipline) {
}
