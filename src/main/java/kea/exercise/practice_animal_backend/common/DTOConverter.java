package kea.exercise.practice_animal_backend.common;

import kea.exercise.practice_animal_backend.participants.Participant;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseBasics;
import org.springframework.stereotype.Service;

@Service
public class DTOConverter {


    public ParticipantResponseBasics toBasicParticipantDTO(Participant participant) {
        return new ParticipantResponseBasics(participant.getId(), participant.getName(), participant.getAge(), participant.getAgeGroup(), participant.getClub(), participant.getGender(), participant.getDisciplines());
    }


}
