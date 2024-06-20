package kea.exercise.practice_animal_backend.participants.dtos;

import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.participants.Club;
import kea.exercise.practice_animal_backend.participants.Gender;
import kea.exercise.practice_animal_backend.results.Result;
import kea.exercise.practice_animal_backend.results.dtos.ResultResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record ParticipantResponseDetail(int id, String name, LocalDate birthDate, int age, String ageGroup, Club club, Gender gender, List<ResultResponseDTO> results, List<Discipline> disciplines) {
}
