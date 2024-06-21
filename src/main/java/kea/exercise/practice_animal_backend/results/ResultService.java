package kea.exercise.practice_animal_backend.results;

import kea.exercise.practice_animal_backend.common.DTOConverter;
import kea.exercise.practice_animal_backend.participants.Participant;
import kea.exercise.practice_animal_backend.participants.ParticipantRepository;
import kea.exercise.practice_animal_backend.results.dtos.ResultResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {
    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final DTOConverter dtoConverter;

    public ResultService(ResultRepository resultRepository, ParticipantRepository participantRepository, DTOConverter dtoConverter) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.dtoConverter = dtoConverter;
    }


    public List<ResultResponseDTO> getResults() {

        return resultRepository.findAll().stream().map(this::toDTO).toList();
    }

    public ResultResponseDTO getResult(int id) {
        return toDTO(resultRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found")));
    }

    public ResultResponseDTO updateResult(int id, Result result) {
        Result existingResult = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
        Participant foundParticipant = participantRepository.findById(result.getParticipant().getId()).orElseThrow(() -> new RuntimeException("Participant not found"));
        if (!foundParticipant.hasDiscipline(result.getDiscipline())) {
            throw new RuntimeException("Participant does not have discipline");
        }
        existingResult.setParticipant(foundParticipant);
        existingResult.setDate(result.getDate());
        existingResult.setResultValue(result.getResultValue());
        existingResult.setDiscipline(result.getDiscipline());
        return toDTO(resultRepository.save(existingResult));
    }

    public ResultResponseDTO toDTO(Result result) {
        return new ResultResponseDTO(result.getId(), result.getResultValue(), result.getResult(), result.getDate(), result.getDiscipline().getResultType(), result.getDiscipline(),
                dtoConverter.toBasicParticipantDTO(result.getParticipant()));
    }

    public boolean allowedToAdd(Result result, Participant participant) {
        return participant.hasDiscipline(result.getDiscipline());
    }


    public void deleteResult(int id) {
        System.out.println("deleting result with id " + id);
        resultRepository.deleteById(id);
    }

    public List<ResultResponseDTO> addResults(List<Result> results) {

        List<Result> validatedResults = new ArrayList<>();
        for (Result result : results) {
            System.out.println("saving this id");
            System.out.println(result.getParticipant().getId());
            Participant participant = participantRepository.findById(result.getParticipant().getId()).orElse(null);
            if (participant!= null && participant.hasDiscipline(result.getDiscipline())) {
                result.setParticipant(participant);
                validatedResults.add(result);
            }
            else {
                System.out.println("Participant does not have discipline");
            }
        }
        return resultRepository.saveAll(validatedResults).stream().map(this::toDTO).toList();
//        Participant participant = participantRepository.findById(participantID).orElseThrow(() -> new RuntimeException("Participant not found"));
//        if (!allowedToAdd(result, participant)) {
//            throw new RuntimeException("Participant does not have discipline");
//        }
//        result.setParticipant(participant);
//        return toDTO(resultRepository.save(result));
    }

    public List<Result> getResultsByParticipantID(int id) {
        return resultRepository.findAllByParticipantId(id);
    }
}
