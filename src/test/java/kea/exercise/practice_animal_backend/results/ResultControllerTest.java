package kea.exercise.practice_animal_backend.results;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Part;
import kea.exercise.practice_animal_backend.disciplines.Discipline;
import kea.exercise.practice_animal_backend.disciplines.DisciplineRepository;
import kea.exercise.practice_animal_backend.participants.Participant;
import kea.exercise.practice_animal_backend.participants.ParticipantRepository;
import kea.exercise.practice_animal_backend.results.dtos.ResultResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ResultControllerTest {
    @Autowired
    private WebTestClient client;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setup() {
        resultRepository.deleteAll();
        resultRepository.findAll().size();
        //disciplineRepository.deleteAll();
    }

    @Test
    @DisplayName("Should not be null")
    void notNull(){
        assertNotNull(client);
    }

    @Test
    @DisplayName("Should return all results")
    void shouldReturnAllResults() {
        client.get().uri("/results")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ResultResponseDTO.class);
    }

    @Test
    @DisplayName("Should return result by id")
    void shouldReturnResultById() {
        Discipline discipline = new Discipline("Test Discipline", ResultType.TIME);
        discipline = disciplineRepository.save(discipline);

        Participant participant = new Participant();
        participant.setName("Test");
        participant = participantRepository.save(participant);

        Result result = new Result();
        result.setResultValue(100);
        result.setDiscipline(discipline);
        result.setParticipant(participant);
        result = resultRepository.save(result);

        Result finalResult = result;
        client.get().uri("/results/" + result.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultResponseDTO.class)
                .consumeWith(response -> {
                    ResultResponseDTO responseBody = response.getResponseBody();
                    assertEquals(finalResult.getId(), responseBody.id());
                    assertEquals(finalResult.getResult(), responseBody.result());
                });
    }

    @Test
    @DisplayName("Should return 404 when result not found")
    void shouldReturnNotFoundWhenResultDoesNotExist() {
        client.get().uri("/results/999")
                .exchange()
                .expectStatus().isNotFound();
    }



    @Test
    @DisplayName("Should update an existing result")
    void shouldUpdateExistingResult() {
        Discipline discipline = new Discipline("Test Discipline", ResultType.TIME);
        discipline = disciplineRepository.save(discipline);

        Participant participant = new Participant();
        participant.setName("Test");
        participant.setDisciplines(List.of(discipline));
        participant = participantRepository.save(participant);


        Result result = new Result();
        result.setParticipant(participant);
        result.setResultValue(100);
        result.setDiscipline(discipline);
        result = resultRepository.save(result);
        result.setResultValue(200);

        Result finalResult = result;
        client.put().uri("/results/" + result.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(result)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultResponseDTO.class)
                .consumeWith(response -> {
                    ResultResponseDTO responseBody = response.getResponseBody();
                    assertEquals(finalResult.getId(), responseBody.id());
                    assertEquals(finalResult.getResult(), responseBody.result());
                });
    }

    @Test
    @DisplayName("Should delete an existing result")
    void shouldDeleteExistingResult() {
        Result result = new Result();
        result.setResultValue(100);
        result = resultRepository.save(result);

        client.delete().uri("/results/" + result.getId())
                .exchange()
                .expectStatus().isOk();

        assertFalse(resultRepository.existsById(result.getId()));
    }
}