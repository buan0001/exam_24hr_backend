package kea.exercise.practice_animal_backend.participants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

}
