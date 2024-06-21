package kea.exercise.practice_animal_backend.disciplines;

import jakarta.persistence.*;
import kea.exercise.practice_animal_backend.results.ResultType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Discipline {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    public Discipline(String name, ResultType type) {
        this.name = name;
        this.resultType = type;
    }
}
