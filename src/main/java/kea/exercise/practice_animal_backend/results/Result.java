package kea.exercise.practice_animal_backend.results;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import kea.exercise.practice_animal_backend.disciplines.Discipline;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate date;
    private double resultValue;
    @ManyToOne
    private Discipline discipline;

    public Result(LocalDate date, double resultValue, Discipline discipline) {
        this.date = date;
        this.resultValue = resultValue;
        this.discipline = discipline;
    }

    String getResult() {
        return resultValue + " " + discipline.getResultType();
//        StringBuilder result = new StringBuilder();
//         if (discipline.getResultType() == ResultType.JUMP) {
//            result.append( resultValue/10);
//        }
//        else if (discipline.getResultType() == ResultType.POINTS) {
//            return String.format("%.2f", resultValue) + " points";
//        }
//        else if (discipline.getResultType() == ResultType.DISTANCE) {
//            return String.format("%.2f", resultValue) + " meters";
//        }
//        else if (discipline.getResultType() == ResultType.TIME) {
//            return String.format("%.2f", resultValue) + " seconds";
//        }
//        else {
//            return "Unknown result type";
//        }
    }
}
