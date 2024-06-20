package kea.exercise.practice_animal_backend.participants.dtos;

import kea.exercise.practice_animal_backend.participants.Gender;

public record ParticipantResponseBasics(int id, String name, int age, String ageGroup, String club, Gender gender) {
}
