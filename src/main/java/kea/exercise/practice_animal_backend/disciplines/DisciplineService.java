package kea.exercise.practice_animal_backend.disciplines;

import org.springframework.stereotype.Service;

@Service
public class DisciplineService {
    DisciplineRepository disciplineRepository;
    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }


    public Iterable<Discipline> getDisciplines() {
        return disciplineRepository.findAll();
    }

    public Discipline createDiscipline(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    public Discipline updateDiscipline(String id, Discipline discipline) {
        if (disciplineRepository.existsById(Integer.parseInt(id))) {
            discipline.setId(Integer.parseInt(id));
            return disciplineRepository.save(discipline);
        }
        return null;
    }
}
