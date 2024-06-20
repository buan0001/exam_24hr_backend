package kea.exercise.practice_animal_backend.results;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    List<Result> findAllByParticipantId(int id);
}
