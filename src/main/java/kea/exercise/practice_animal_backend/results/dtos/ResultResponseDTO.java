package kea.exercise.practice_animal_backend.results.dtos;

import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.participants.Participant;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseBasics;
import kea.exercise.practice_animal_backend.results.ResultType;

import java.time.LocalDate;

public record ResultResponseDTO (int id, double resultValue, String result, LocalDate date, ResultType resultType, Discipline discipline, ParticipantResponseBasics participant ){
}
