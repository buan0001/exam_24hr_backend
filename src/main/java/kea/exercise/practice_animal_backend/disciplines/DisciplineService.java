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
}
