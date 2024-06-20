package kea.exercise.practice_animal_backend.disciplines;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
    private String name;
    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    public Discipline(String name, ResultType type) {
        this.name = name;
        this.resultType = type;
    }
}
