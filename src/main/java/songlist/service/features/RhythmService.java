package songlist.service.features;

import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.mappers.entity.SongMapper;
import songlist.model.features.rhythm.Rhythm;
import songlist.model.features.rhythm.dto.NewRhythmDTO;
import songlist.model.features.rhythm.dto.RhythmDTO;
import songlist.model.song.dto.SongDTO;
import songlist.repository.impl.sql.RhythmRepositoryImpl;

import java.util.*;
import java.util.stream.Collectors;

import static songlist.utils.Constants.DOES_NOT_EXIST;

@Service
public class RhythmService {

    RhythmRepositoryImpl rhythmRepository;

    public RhythmService(RhythmRepositoryImpl rhythmRepository) {
        this.rhythmRepository = rhythmRepository;
    }

    public List<RhythmDTO> getAll() {
        return rhythmRepository.findAll().stream().map(s -> new RhythmDTO(s.getId().toString(), s.getName(), s.getMeter())).collect(Collectors.toList());
    }

    public RhythmDTO getDTO(String id) {
        Rhythm rhythm = rhythmRepository.findById(UUID.fromString(id)).orElseThrow(
            NoSuchElementException::new);
        return new RhythmDTO(rhythm.getId().toString(), rhythm.getName(), rhythm.getMeter());
    }

    public Rhythm get(String id) throws ValidationException {
        Optional<Rhythm> rhythm = rhythmRepository.findById(UUID.fromString(id));

        if (rhythm.isEmpty()) {
            throw new ValidationException("Rhythm with id : " + id + DOES_NOT_EXIST);
        }
        return rhythm.get();
    }

    public String create(NewRhythmDTO newRhythmDTO) {
        Rhythm rhythm = new Rhythm();
        rhythm.setName(newRhythmDTO.getName());
        rhythm.setMeter(newRhythmDTO.getMeter());

        return rhythmRepository.save(rhythm).getId().toString();
    }

    public String update(String id, NewRhythmDTO newRhythmDTO) throws ValidationException {
        Optional<Rhythm> rhythm = rhythmRepository.findById(UUID.fromString(id));

        if (rhythm.isEmpty()) {
            throw new ValidationException("No rhythm with id: " + id);
        }
        Rhythm r = rhythm.get();
        r.setName(newRhythmDTO.getName());
        r.setMeter(newRhythmDTO.getMeter());

        return rhythmRepository.save(r).getId().toString();
    }

    public void delete(String id) {
        rhythmRepository.deleteById(UUID.fromString(id));
    }

    public Set<SongDTO> getSongs(String id) {
        try {
            return this.get(id).getSongs().stream().map(SongMapper::toSongDTO).collect(Collectors.toSet());
        } catch (ValidationException e) {
            return Set.of();
        }
    }
}
