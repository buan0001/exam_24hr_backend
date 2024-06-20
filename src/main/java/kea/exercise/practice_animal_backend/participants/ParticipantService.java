package kea.exercise.practice_animal_backend.participants;

import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseBasics;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseDetail;
import kea.exercise.practice_animal_backend.results.Result;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<ParticipantResponseBasics> getParticipants() {
        return participantRepository.findAll().stream().map(this::toBasicDTO).toList();
    }

    public ParticipantResponseDetail getParticipant(int id) {
        return toDetailDTO(participantRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Participant not found"))) ;
    }

    private Participant findParticipant(int id) {
        return participantRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Participant not found"));
    }

    public ParticipantResponseDetail createParticipant(Participant participant) {
        return toDetailDTO(participantRepository.save(participant)) ;
    }

    public ParticipantResponseDetail addResult(int id, Result result) {
        Participant participant = findParticipant(id);
        participant.addResult(result);
        return toDetailDTO(participantRepository.save(participant)) ;
    }

    public void deleteParticipant(int id) {
        participantRepository.deleteById(id);
    }

    public ParticipantResponseDetail updateParticipant(int id, Participant participant) {
        Participant existingParticipant = findParticipant(id);
        existingParticipant.setName(participant.getName());
        existingParticipant.setBirthDate(participant.getBirthDate());
        existingParticipant.setClub(participant.getClub());
        return toDetailDTO(participantRepository.save(existingParticipant)) ;
    }

    public ParticipantResponseDetail toDetailDTO(Participant participant) {
        return new ParticipantResponseDetail(participant.getId(), participant.getName(), participant.getAge(), participant.getAgeGroup(), participant.getClub(), participant.getGender(), participant.getResults(), participant.getDisciplines());
    }

    public ParticipantResponseBasics toBasicDTO(Participant participant) {
        return new ParticipantResponseBasics(participant.getId(), participant.getName(), participant.getAge(), participant.getAgeGroup(), participant.getClub(), participant.getGender());
    }

}
