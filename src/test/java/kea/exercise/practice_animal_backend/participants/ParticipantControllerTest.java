package kea.exercise.practice_animal_backend.participants;

import kea.exercise.practice_animal_backend.participants.Participant;
import kea.exercise.practice_animal_backend.participants.ParticipantRepository;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseBasics;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseDetail;
import kea.exercise.practice_animal_backend.results.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ParticipantControllerTest {
    @Autowired
    private WebTestClient client;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ResultRepository resultRepository;

    @BeforeEach
    void setup() {
        resultRepository.deleteAll();
        participantRepository.deleteAll();
    }

    @Test
    @DisplayName("Should not be null")
    void notNull(){
        assertNotNull(client);
    }

    @Test
    @DisplayName("Should return all participants")
    void shouldReturnAllParticipants() {
        client.get().uri("/participants")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ParticipantResponseBasics.class);
    }

    @Test
    @DisplayName("Should return participant by id")
    void shouldReturnParticipantById() {
        Participant participant = new Participant();
        participant.setName("Test");
        participant = participantRepository.save(participant);

        Participant finalParticipant = participant;
        client.get().uri("/participants/" + participant.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ParticipantResponseDetail.class)
                .consumeWith(response -> {
                    ParticipantResponseDetail responseBody = response.getResponseBody();
                    assertEquals(finalParticipant.getId(), responseBody.id());
                    assertEquals(finalParticipant.getName(), responseBody.name());
                });
    }

    @Test
    @DisplayName("Should return 404 when participant not found")
    void shouldReturnNotFoundWhenParticipantDoesNotExist() {
        client.get().uri("/participants/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("Should create a new participant")
    void shouldCreateNewParticipant() {
        Participant participant = new Participant();
        participant.setName("Test");

        client.post().uri("/participants")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(participant)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ParticipantResponseDetail.class)
                .consumeWith(response -> {
                    ParticipantResponseDetail responseBody = response.getResponseBody();
                    assertEquals(participant.getName(), responseBody.name());
                });
    }

    @Test
    @DisplayName("Should update an existing participant")
    void shouldUpdateExistingParticipant() {
        Participant participant = new Participant();
        participant.setName("Test");
        participant = participantRepository.save(participant);
        participant.setName("Updated");

        Participant finalParticipant = participant;
        client.put().uri("/participants/" + participant.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(participant)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ParticipantResponseDetail.class)
                .consumeWith(response -> {
                    ParticipantResponseDetail responseBody = response.getResponseBody();
                    assertEquals(finalParticipant.getId(), responseBody.id());
                    assertEquals(finalParticipant.getName(), responseBody.name());
                });
    }

    @Test
    @DisplayName("Should delete an existing participant")
    void shouldDeleteExistingParticipant() {
        Participant participant = new Participant();
        participant.setName("Test");
        participant = participantRepository.save(participant);

        client.delete().uri("/participants/" + participant.getId())
                .exchange()
                .expectStatus().isOk();

        assertFalse(participantRepository.existsById(participant.getId()));
    }
}