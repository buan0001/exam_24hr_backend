package kea.exercise.practice_animal_backend.participants;

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

    @Test
    void notNull(){
        assertNotNull(client);
    }

//    @Test
//    void addResult() {
//    Participant found = participantRepository.findById(2).orElseThrow(()->new RuntimeException("Participant not found"));
//        System.out.println("found" + found  );
//        client.post().uri("/participants/2/addResult").contentType(MediaType.APPLICATION_JSON)
//                .bodyValue("""
//                        {
//                        "date": "2021-09-01",
//                        "resultValue": 10.0,
//                        "discipline": {
//                        "name": "High Jump"}
//
//                        }
//                        """).exchange().expectStatus().isOk();
//    }
}