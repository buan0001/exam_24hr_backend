package kea.exercise.practice_animal_backend.participants.dtos;

import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.participants.Club;
import kea.exercise.practice_animal_backend.participants.Gender;

import java.util.List;

public record ParticipantResponseBasics(int id, String name, int age, String ageGroup, Club club, Gender gender, List<Discipline> disciplines) {
}
