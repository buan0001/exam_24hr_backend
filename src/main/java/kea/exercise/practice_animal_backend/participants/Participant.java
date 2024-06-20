package kea.exercise.practice_animal_backend.participants;

import jakarta.persistence.*;
import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.results.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Participant {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private LocalDate birthDate;
    private String club;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany
    private List<Result> results;

    @ManyToMany
    private List<Discipline> disciplines;

    public Participant(String name, LocalDate birthDate, String club, Gender gender, List<Discipline> disciplines) {
        this.name = name;
        this.birthDate = birthDate;
        this.club = club;
        this.disciplines = disciplines;
        this.gender = gender;
    }
    public Participant(String name, LocalDate birthDate, String club,  Gender gender, List<Result> results, List<Discipline> disciplines) {
        this.name = name;
        this.birthDate = birthDate;
        this.club = club;
        this.results = results;
        this.disciplines = disciplines;
        this.gender = gender;
    }

    int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    String getAgeGroup(){
        int age = getAge();
        if (age < 12) {
            return "U12";
        } else if (age < 15) {
            return "U15";
        } else if (age < 18) {
            return "U18";
        } else {
            return "Senior";
        }
    }

    public void addResult(Result result) {
        if ( hasDiscipline(result.getDiscipline()) ) {
            results.add(result);
        } else {
            throw new IllegalArgumentException("Participant does not have discipline " + result.getDiscipline().getName());
        }
    }

    private boolean hasDiscipline(Discipline discipline) {
        for (Discipline d : disciplines) {
            if (d.getName().equals(discipline.getName()) ) {
                return true;
            }
        }
        return false;
    }
}
