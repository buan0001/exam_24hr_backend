package kea.exercise.practice_animal_backend.participants;

import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseBasics;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseDetail;
import kea.exercise.practice_animal_backend.results.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/participants")
@CrossOrigin(origins = "http://localhost:5173")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    // allow sorting by various fields, also reversable
    @GetMapping
    public List<ParticipantResponseBasics> getParticipants(@RequestParam(required = false, defaultValue = "name") String sortBy, @RequestParam(required = false) String sortOrder) {
        System.out.println("get participants 1");
        System.out.println("sort by:" + sortBy);
        System.out.println("sort order:" + sortOrder);
        return participantService.getParticipants(sortBy, sortOrder);
    }

//    @GetMapping("/?{sortOrder}")
//    public List<ParticipantResponseBasics> getParticipantsSorted(@PathVariable String sortOrder) {
//        System.out.println("get participants SORTED");
//        System.out.println(sortOrder);
//        return participantService.getParticipants();
//    }

    @GetMapping("/{id}")
    public ParticipantResponseDetail getParticipant(@PathVariable int id) {
        System.out.println("get participant");
        try {
            return participantService.getParticipant(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/clubs")
    public List<Club> getClubs() {
        System.out.println("get clubs");
        return participantService.getClubs();
    }


    @PostMapping
    public ParticipantResponseDetail createParticipant(@RequestBody Participant participant) {
        System.out.println("create participant");

        return participantService.createParticipant(participant);
    }

    @PutMapping("/{id}")
    public ParticipantResponseDetail updateParticipant(@PathVariable int id, @RequestBody Participant participant) {
        System.out.println("update participant");
        return participantService.updateParticipant(id, participant);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable int id) {
        System.out.println("delete participant");

        participantService.deleteParticipant(id);
    }


}
