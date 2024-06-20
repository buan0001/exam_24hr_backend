package kea.exercise.practice_animal_backend.config;

import kea.exercise.practice_animal_backend.participants.Gender;
import kea.exercise.practice_animal_backend.participants.Participant;
import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.results.ResultType;
import kea.exercise.practice_animal_backend.results.Result;
import kea.exercise.practice_animal_backend.participants.ParticipantRepository;
import kea.exercise.practice_animal_backend.disciplines.DisciplineRepository;
import kea.exercise.practice_animal_backend.results.ResultTypeDiscipline;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitData implements CommandLineRunner {

    private final List<Discipline> disciplines = new ArrayList<>();
    private final List<Result> results = new ArrayList<>();
    private final List<Participant> participants = new ArrayList<>();
    private final ParticipantRepository participantRepository;

    private final ResultTypeDiscipline resultTypeDiscipline;

    private final DisciplineRepository disciplineRepository;

    public InitData(ParticipantRepository participantRepository, ResultTypeDiscipline resultTypeDiscipline, DisciplineRepository disciplineRepository) {
        this.participantRepository = participantRepository;
        this.resultTypeDiscipline = resultTypeDiscipline;
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        initDisciplines();
        initResults();
        initParticipants();
    }

    private void initDisciplines() {
        disciplines.add(new Discipline("Discus", ResultType.DISTANCE));
        disciplines.add(new Discipline("100m Hurdles", ResultType.TIME));
        disciplines.add(new Discipline("High Jump", ResultType.JUMP));
        disciplines.add(new Discipline("Long Jump", ResultType.JUMP));
        disciplines.add(new Discipline("100m Sprint", ResultType.TIME));
        disciplines.add(new Discipline("Shot Put", ResultType.DISTANCE));
        disciplines.add(new Discipline("Pole Vault", ResultType.JUMP));
        disciplines.add(new Discipline("Javelin", ResultType.DISTANCE));
        disciplines.add(new Discipline("Hammer Throw", ResultType.DISTANCE));
        disciplines.add(new Discipline("Triple Jump", ResultType.JUMP));
        disciplines.add(new Discipline("Decathlon", ResultType.POINTS));
        disciplines.add(new Discipline("800m", ResultType.TIME));

        disciplineRepository.saveAll(disciplines);
    }

    private void initResults() {
        Random random = new Random();
        for (Discipline discipline : disciplines) {
            for (int j = 0; j < 3; j++) {
                if (discipline.getResultType() == ResultType.DISTANCE) {
                    results.add(new Result(LocalDate.now(), random.nextDouble(15, 65), discipline));
                } else if (discipline.getResultType() == ResultType.JUMP) {
                    results.add(new Result(LocalDate.now(), random.nextDouble(1, 3), discipline));
                } else if (discipline.getResultType() == ResultType.TIME) {
                    results.add(new Result(LocalDate.now(), random.nextDouble(10 * 1000, 240 * 1000), discipline));
                } else if (discipline.getResultType() == ResultType.POINTS) {
                    results.add(new Result(LocalDate.now(), random.nextDouble(100, 1000), discipline));

                }
            }

            resultTypeDiscipline.saveAll(results);
        }
    }


    private void initParticipants() {
        participants.add(new Participant("Frederik Larsen", LocalDate.of(2001, 1, 4), "Amager AC", Gender.MAN, List.of(results.get(0), results.get(5)), disciplines));
        participants.add(new Participant("Henrik Jensen", LocalDate.of(2001, 2, 6), "Sparta", Gender.MAN, List.of(results.get(1), results.get(4)), disciplines));
        participants.add(new Participant("Emilie Prag", LocalDate.of(2001, 3, 5), "Herlev IF", Gender.WOMAN, List.of(results.get(2), results.get(3)), disciplines));
        participants.add(new Participant("Freja Juul", LocalDate.of(2001, 4, 9), "Roskilde AC", Gender.WOMAN, List.of(results.get(6), results.get(10)), disciplines));
        participants.add(new Participant("Hermann Bang", LocalDate.of(2001, 5, 11), "Køge Løbeklub", Gender.MAN, List.of(results.get(7), results.get(9)), disciplines));
        participants.add(new Participant("Louise Skovlund", LocalDate.of(2001, 6, 21), "Fremmad Amager", Gender.OTHER, List.of(results.get(8), results.get(11)), disciplines));
        participants.add(new Participant("Gustav Gaard", LocalDate.of(2001, 7, 14), "Greve Atletik", Gender.OTHER, List.of(disciplines.get(6), disciplines.get(7), disciplines.get(8))));

        participantRepository.saveAll(participants);
    }
}
