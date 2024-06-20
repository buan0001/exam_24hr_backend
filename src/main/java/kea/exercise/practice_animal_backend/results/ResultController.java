package kea.exercise.practice_animal_backend.results;

import kea.exercise.practice_animal_backend.results.dtos.ResultResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
@CrossOrigin(origins = "http://localhost:5173")
public class ResultController {
    private final ResultService resultService;
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public List<ResultResponseDTO> getResults() {
        return resultService.getResults();
    }

    @GetMapping("/{id}")
    public ResultResponseDTO getResult(@PathVariable int id) {
        return resultService.getResult(id);
    }

    @PutMapping("/{id}")
    public ResultResponseDTO updateResult(@PathVariable int id, @RequestBody Result result) {
        return resultService.updateResult(id, result);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable int id) {
        resultService.deleteResult(id);
    }
}
