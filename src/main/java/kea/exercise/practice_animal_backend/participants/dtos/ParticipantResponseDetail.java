package kea.exercise.practice_animal_backend.participants.dtos;

import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.participants.Gender;
import kea.exercise.practice_animal_backend.results.Result;

import java.util.List;

public record ParticipantResponseDetail(int id, String name, int age, String ageGroup, String club, Gender gender, List<Result> results, List<Discipline> disciplines) {
}
