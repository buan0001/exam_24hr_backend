package kea.exercise.practice_animal_backend.participants;

import kea.exercise.practice_animal_backend.common.DTOConverter;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseBasics;
import kea.exercise.practice_animal_backend.participants.dtos.ParticipantResponseDetail;
import kea.exercise.practice_animal_backend.results.Result;
import kea.exercise.practice_animal_backend.results.ResultRepository;
import kea.exercise.practice_animal_backend.results.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private ResultService resultService;

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private DTOConverter dtoConverter;

    @InjectMocks
    private ParticipantService participantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Should throw exception when participant not found")
    void shouldThrowExceptionWhenParticipantNotFound() {
        when(participantRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> participantService.getParticipant(1));

        verify(participantRepository, times(1)).findById(anyInt());
    }


    @Test
    @DisplayName("Should delete participant and associated results")
    void shouldDeleteParticipantAndAssociatedResults() {
        Result result = new Result();
        when(resultRepository.findAllByParticipantId(anyInt())).thenReturn(Arrays.asList(result));

        participantService.deleteParticipant(1);

        verify(resultRepository, times(1)).findAllByParticipantId(anyInt());
        verify(resultRepository, times(1)).deleteAll(Arrays.asList(result));
        verify(participantRepository, times(1)).deleteById(anyInt());
    }

}