package kea.exercise.practice_animal_backend.results;

import jakarta.persistence.*;
import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.participants.Participant;
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
    @ManyToOne
    private Participant participant;
    public Result(LocalDate date, double resultValue, Discipline discipline) {
        this.date = date;
        this.resultValue = resultValue;
        this.discipline = discipline;
    }

    public Result(LocalDate date, double resultValue, Discipline discipline, Participant participant) {
        this.date = date;
        this.resultValue = resultValue;
        this.discipline = discipline;
        this.participant = participant;
    }

    public String getResult() {
        String result;
        switch (discipline.getResultType()) {
            case JUMP_HEIGHT, DISTANCE_THROWN, JUMP_LENGTH:
                result = String.format("%.2fm", resultValue);
                break;
            case POINTS:
                result = String.format("%.2f points", resultValue);
                break;
            case TIME:
                int totalSeconds = (int) (resultValue / 1000);
                int minutes = totalSeconds / 60;
                double remainder = (resultValue / 1000.0) % 60;
                int seconds = (int) remainder;
                int milliseconds = (int) ((remainder - seconds) * 1000);
                if (minutes == 0) {
                    result = String.format("%d.%03d", seconds, milliseconds);
                    break;
                } else {
                    result = String.format("%d.%02d.%03d", minutes, seconds, milliseconds);
                }

                break;
            default:
                result = "Unknown result type";
        }
        return result;
    }

//    String getResult() {
//
//        StringBuilder result = new StringBuilder();
//         if (discipline.getResultType() == ResultType.JUMP_HEIGHT) {
//            if (resultValue > 100) {
//                result.append();
//
//            }
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
//    }
}
