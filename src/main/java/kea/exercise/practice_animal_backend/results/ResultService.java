package kea.exercise.practice_animal_backend.results;

import kea.exercise.practice_animal_backend.common.DTOConverter;
import kea.exercise.practice_animal_backend.participants.Participant;
import kea.exercise.practice_animal_backend.participants.ParticipantRepository;
import kea.exercise.practice_animal_backend.participants.ParticipantService;
import kea.exercise.practice_animal_backend.results.dtos.ResultResponseDTO;
import org.springframework.stereotype.Service;

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
        return toDTO(resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found")));
    }

    public ResultResponseDTO updateResult(int id, Result result) {
        Result existingResult = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
        existingResult.setDate(result.getDate());
        existingResult.setResultValue(result.getResultValue());
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
        resultRepository.deleteById(id);
    }

    public ResultResponseDTO addResult(int participantID, Result result) {
        Participant participant = participantRepository.findById(participantID).orElseThrow(() -> new RuntimeException("Participant not found"));
        if (!allowedToAdd(result, participant)) {
            throw new RuntimeException("Participant does not have discipline");
        }
        result.setParticipant(participant);
        return toDTO(resultRepository.save(result));
    }

    public List<ResultResponseDTO> getResultsByParticipantID(int id) {
        return resultRepository.findAllByParticipantId(id).stream().map(this::toDTO).toList();
    }
}
