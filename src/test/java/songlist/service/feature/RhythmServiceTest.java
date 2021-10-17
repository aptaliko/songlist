package songlist.service.feature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import songlist.exceptions.ValidationException;
import songlist.model.features.rhythm.Rhythm;
import songlist.model.features.rhythm.dto.RhythmDTO;
import songlist.repository.features.RhythmRepository;
import songlist.service.Utils;
import songlist.service.features.RhythmService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RhythmServiceTest {

    @Autowired
    RhythmService rhythmService;

    @MockBean
    RhythmRepository rhythmRepository;

    @Test
    public void shouldReturnTheRhythmOfId() throws ValidationException {
        Rhythm rhythm = Utils.createRandomRhythm();

        when(rhythmRepository.findById(rhythm.getId())).thenReturn(Optional.of(rhythm));

        Rhythm r1 = rhythmService.get(rhythm.getId().toString());

        assertEquals(r1.getId(), rhythm.getId());
        assertEquals(r1.getName(), rhythm.getName());
        assertEquals(r1.getMeter(), rhythm.getMeter());
    }

    @Test
    public void shouldThrowExceptionIfIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(rhythmRepository.findById(id)).thenReturn(Optional.empty());

        try {
            rhythmService.get(id.toString());
        } catch (Exception e) {
            assertTrue(e instanceof ValidationException);
            assertEquals(e.getMessage(), "Rhythm with id : " + id + " does not exist.");
        }
    }

    @Test
    public void shouldReturnAllRhythms() {
        List<Rhythm> rhythms = Utils.createRandomRhythms(2);

        when(rhythmRepository.findAll()).thenReturn(rhythms);

        List<RhythmDTO> rhythmDTOs = rhythmService.getAll();

        assertEquals(rhythmDTOs.get(0).getId(), rhythms.get(0).getId().toString());
        assertEquals(rhythmDTOs.get(0).getName(), rhythms.get(0).getName());
        assertEquals(rhythmDTOs.get(0).getMeter(), rhythms.get(0).getMeter());
        assertEquals(rhythmDTOs.get(1).getId(), rhythms.get(1).getId().toString());
        assertEquals(rhythmDTOs.get(1).getName(), rhythms.get(1).getName());
        assertEquals(rhythmDTOs.get(1).getMeter(), rhythms.get(1).getMeter());
    }
}
