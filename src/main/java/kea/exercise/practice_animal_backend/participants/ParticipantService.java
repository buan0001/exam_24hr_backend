package kea.exercise.practice_animal_backend.participants;

import kea.exercise.practice_animal_backend.common.DTOConverter;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseBasics;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseDetail;
import kea.exercise.practice_animal_backend.results.Result;
import kea.exercise.practice_animal_backend.results.ResultRepository;
import kea.exercise.practice_animal_backend.results.ResultService;
import kea.exercise.practice_animal_backend.results.dtos.ResultResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ResultService resultService;

    private final ResultRepository resultRepository;
    private final DTOConverter dtoConverter;

    public ParticipantService(ParticipantRepository participantRepository, ResultService resultService, DTOConverter dtoConverter, ResultRepository resultRepository) {
        this.participantRepository = participantRepository;
        this.resultService = resultService;
        this.dtoConverter = dtoConverter;
        this.resultRepository = resultRepository;
    }

    public List<ParticipantResponseBasics> getParticipants() {
        return participantRepository.findAll().stream().map(participant -> dtoConverter.toBasicParticipantDTO(participant)).toList();
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



    public void deleteParticipant(int id) {

        List<Result> associatedResults = resultRepository.findAllByParticipantId(id);
        resultRepository.deleteAll(associatedResults);
        associatedResults.size();
        resultRepository.flush();
        //flush so I can delete the participant


        participantRepository.deleteById(id);
    }

    public ParticipantResponseDetail updateParticipant(int id, Participant participant) {
        Participant existingParticipant = findParticipant(id);
        existingParticipant.setName(participant.getName());
        existingParticipant.setBirthDate(participant.getBirthDate());
        existingParticipant.setClub(participant.getClub());
        existingParticipant.setGender(participant.getGender());
        existingParticipant.setDisciplines(participant.getDisciplines());
        return toDetailDTO(participantRepository.save(existingParticipant)) ;
    }

    public ParticipantResponseDetail toDetailDTO(Participant participant) {
    List<ResultResponseDTO> foundResults = resultService.getResultsByParticipantID(participant.getId()).stream().map(result -> resultService.toDTO(result)).toList();
        return new ParticipantResponseDetail(participant.getId(), participant.getName(), participant.getBirthDate(), participant.getAge(), participant.getAgeGroup(), participant.getClub(), participant.getGender(),
                foundResults, participant.getDisciplines());
    }



    public List<Club> getClubs() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream().map(Participant::getClub).distinct().toList();
    }


}
