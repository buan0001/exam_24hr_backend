package kea.exercise.practice_animal_backend.results;

import kea.exercise.practice_animal_backend.results.dtos.ResultResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<ResultResponseDTO> getResults() {
        return resultRepository.findAll().stream().map(this::toDTO).toList();
    }

    public ResultResponseDTO getResult(int id) {
        return toDTO(resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"))) ;
    }

    public ResultResponseDTO updateResult(int id, Result result) {
        Result existingResult = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
        existingResult.setDate(result.getDate());
        existingResult.setResultValue(result.getResultValue());
        return toDTO(resultRepository.save(existingResult)) ;
    }

    public ResultResponseDTO toDTO(Result result) {
        return new ResultResponseDTO(result.getId(), result.getResult(), result.getDate(), result.getDiscipline().getResultType(), result.getDiscipline().getName());
    }

    public void deleteResult(int id) {
        resultRepository.deleteById(id);
    }
}
