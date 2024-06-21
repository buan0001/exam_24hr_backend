package kea.exercise.practice_animal_backend.disciplines;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Discipline createDiscipline(@RequestBody Discipline discipline) {
        return disciplineService.createDiscipline(discipline);
    }
    @PutMapping("/{id}")
    public Discipline updateDiscipline(@PathVariable String id, @RequestBody Discipline discipline) {
        return disciplineService.updateDiscipline(id, discipline);
    }
}
