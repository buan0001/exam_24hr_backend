package kea.exercise.practice_animal_backend.disciplines;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplines")
@CrossOrigin(origins = "http://localhost:5173")
public class DisciplineController {
    private final DisciplineService disciplineService;
    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public Iterable<Discipline> getDisciplines() {
        return disciplineService.getDisciplines();
    }
}
