package kea.exercise.practice_animal_backend.results;

import kea.exercise.practice_animal_backend.results.dtos.ResultResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping
    public List<ResultResponseDTO> addResults(@RequestBody List<Result> results) {
        System.out.println("add results");
        return resultService.addResults( results);
    }

    @PutMapping("/{id}")
    public ResultResponseDTO updateResult(@PathVariable int id, @RequestBody Result result) {
        try {
            return resultService.updateResult(id, result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable int id) {
        resultService.deleteResult(id);
    }
}
