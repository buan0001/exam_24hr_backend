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
    private int resultValue;
    @ManyToOne
    private Discipline discipline;
    @ManyToOne
    private Participant participant;
    public Result(LocalDate date, int resultValue, Discipline discipline) {
        this.date = date;
        this.resultValue = resultValue;
        this.discipline = discipline;
    }

    public Result(LocalDate date, int resultValue, Discipline discipline, Participant participant) {
        this.date = date;
        this.resultValue = resultValue;
        this.discipline = discipline;
        this.participant = participant;
    }

    public String getResult() {
        String result;
        switch (discipline.getResultType()) {
            case THROW:
            case JUMP, POINTS:
                result = String.format("%d", resultValue);
                break;
            case TIME:
                int totalSeconds = (int) (resultValue / 1000);
                int minutes = totalSeconds / 60;
                double remainder = (resultValue / 1000.0) % 60;
                int seconds = (int) remainder;
                int milliseconds = (int) Math.round((remainder - seconds) * 1000);
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

}
