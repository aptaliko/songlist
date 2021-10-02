package songlist.service.rhythm;

import org.springframework.stereotype.Service;
import songlist.model.features.rhythm.Rhythm;
import songlist.model.features.rhythm.dto.NewRhythmDTO;
import songlist.model.features.rhythm.dto.RhythmDTO;
import songlist.repository.rhythm.RhythmRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RhythmService {

    RhythmRepository rhythmRepository;

    public RhythmService(RhythmRepository rhythmRepository) {
        this.rhythmRepository = rhythmRepository;
    }

    public List<RhythmDTO> getAll() {
        return rhythmRepository.findAll().stream().map(s -> new RhythmDTO(s.getId().toString(), s.getName(), s.getMeter())).collect(Collectors.toList());
    }

    public RhythmDTO get(UUID id) {
        Rhythm rhythm = rhythmRepository.getOne(id);

        return new RhythmDTO(rhythm.getId().toString(), rhythm.getName(), rhythm.getMeter());
    }

    public Optional<UUID> create(NewRhythmDTO newRhythmDTO) {

        if (newRhythmDTO == null) {
            return Optional.empty();
        }
        Rhythm rhythm = new Rhythm();
        rhythm.setName(newRhythmDTO.getName());
        rhythm.setMeter(newRhythmDTO.getMeter());

        return Optional.of(rhythmRepository.save(rhythm).getId());
    }
}
