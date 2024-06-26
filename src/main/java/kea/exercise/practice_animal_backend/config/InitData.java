package kea.exercise.practice_animal_backend.config;

import kea.exercise.practice_animal_backend.participants.Club;
import kea.exercise.practice_animal_backend.participants.Gender;
import kea.exercise.practice_animal_backend.participants.Participant;
import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.results.ResultType;
import kea.exercise.practice_animal_backend.results.Result;
import kea.exercise.practice_animal_backend.participants.ParticipantRepository;
import kea.exercise.practice_animal_backend.disciplines.DisciplineRepository;
import kea.exercise.practice_animal_backend.results.ResultRepository;
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

    private final ResultRepository resultRepository;

    private final DisciplineRepository disciplineRepository;

    public InitData(ParticipantRepository participantRepository, ResultRepository resultRepository, DisciplineRepository disciplineRepository) {
        this.participantRepository = participantRepository;
        this.resultRepository = resultRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        initDisciplines();

        initClubs();
        initParticipants();
        initResults();
    }

    private void initDisciplines() {
        disciplines.add(new Discipline("Discus", ResultType.THROW));
        disciplines.add(new Discipline("100m Hurdles", ResultType.TIME));
        disciplines.add(new Discipline("High Jump", ResultType.JUMP));
        disciplines.add(new Discipline("Long Jump", ResultType.JUMP));
        disciplines.add(new Discipline("100m Sprint", ResultType.TIME));
        disciplines.add(new Discipline("Shot Put", ResultType.THROW));
        disciplines.add(new Discipline("Pole Vault", ResultType.JUMP));
        disciplines.add(new Discipline("Javelin", ResultType.THROW));
        disciplines.add(new Discipline("Hammer Throw", ResultType.THROW));
        disciplines.add(new Discipline("Triple Jump", ResultType.JUMP));
        disciplines.add(new Discipline("Decathlon", ResultType.POINTS));
        disciplines.add(new Discipline("800m", ResultType.TIME));

        disciplineRepository.saveAll(disciplines);
    }

    private void initResults() {
        Random random = new Random();
        for (Discipline discipline : disciplines) {
            for (int j = 0; j < 3; j++) {
                if (discipline.getResultType() == ResultType.THROW) {
                    results.add(new Result(LocalDate.now(), random.nextInt(1500, 6500), discipline, participants.get(random.nextInt(participants.size()))));
                } else if (discipline.getResultType() == ResultType.JUMP){
                    results.add(new Result(LocalDate.now(), random.nextInt(100, 300), discipline,participants.get(random.nextInt(participants.size()))));
                } else if (discipline.getResultType() == ResultType.TIME) {
                    results.add(new Result(LocalDate.now(), random.nextInt(10 * 1000, 240 * 1000), discipline,participants.get(random.nextInt(participants.size()))));
                } else if (discipline.getResultType() == ResultType.POINTS) {
                    results.add(new Result(LocalDate.now(), random.nextInt(100, 1000), discipline,participants.get(random.nextInt(participants.size()))));

                }
            }

            resultRepository.saveAll(results);
        }
    }


    private void initClubs(){

    }

    private void initParticipants() {
        Random random = new Random();
        participants.add(new Participant("Frederik Larsen", LocalDate.of(random.nextInt(1990, 2015), 1, 4), Club.Amager_AC, Gender.MAN, disciplines));
        participants.add(new Participant("Henrik Jensen", LocalDate.of(random.nextInt(1990, 2015), 2, 6), Club.Amager_AC, Gender.MAN, disciplines));
        participants.add(new Participant("Emilie Prag", LocalDate.of(random.nextInt(1990, 2015), 3, 5), Club.BAC, Gender.WOMAN, disciplines));
        participants.add(new Participant("Freja Juul", LocalDate.of(random.nextInt(1990, 2015), 4, 9), Club.BAC, Gender.WOMAN, disciplines));
        participants.add(new Participant("Hermann Bang", LocalDate.of(random.nextInt(1990, 2015), 5, 11), Club.Sparta, Gender.MAN, disciplines));
        participants.add(new Participant("Louise Skovlund", LocalDate.of(random.nextInt(1990, 2015), 6, 21), Club.Roskilde_AC, Gender.OTHER, disciplines));
        participants.add(new Participant("Gustav Gaard", LocalDate.of(random.nextInt(1990, 2015), 7, 14), Club.KIF, Gender.OTHER, List.of(disciplines.get(6), disciplines.get(7), disciplines.get(8))));
//participants.add(new Participant("Frederik Larsen", LocalDate.of(random.nextInt(1990, 2015), 1, 4), Club.Amager_AC, Gender.MAN, List.of(results.get(0), results.get(5)), disciplines));
//        participants.add(new Participant("Henrik Jensen", LocalDate.of(random.nextInt(1990, 2015), 2, 6), Club.Amager_AC, Gender.MAN, List.of(results.get(1), results.get(4)), disciplines));
//        participants.add(new Participant("Emilie Prag", LocalDate.of(random.nextInt(1990, 2015), 3, 5), Club.BAC, Gender.WOMAN, List.of(results.get(2), results.get(3)), disciplines));
//        participants.add(new Participant("Freja Juul", LocalDate.of(random.nextInt(1990, 2015), 4, 9), Club.BAC, Gender.WOMAN, List.of(results.get(6), results.get(10)), disciplines));
//        participants.add(new Participant("Hermann Bang", LocalDate.of(random.nextInt(1990, 2015), 5, 11), Club.Sparta, Gender.MAN, List.of(results.get(7), results.get(9)), disciplines));
//        participants.add(new Participant("Louise Skovlund", LocalDate.of(random.nextInt(1990, 2015), 6, 21), Club.Roskilde_AC, Gender.OTHER, List.of(results.get(8), results.get(11)), disciplines));
//        participants.add(new Participant("Gustav Gaard", LocalDate.of(random.nextInt(1990, 2015), 7, 14), Club.KIF, Gender.OTHER, List.of(disciplines.get(6), disciplines.get(7), disciplines.get(8))));

        participantRepository.saveAll(participants);
    }
}
