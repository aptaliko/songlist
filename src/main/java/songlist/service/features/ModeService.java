package songlist.service.features;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.mappers.entity.SongMapper;
import songlist.model.features.mode.Mode;
import songlist.model.features.mode.dto.ModeDTO;
import songlist.model.features.mode.dto.NewModeDTO;
import songlist.model.song.dto.SongDTO;
import songlist.repository.impl.sql.ModeRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static songlist.utils.Constants.DOES_NOT_EXIST;

@Service
public class ModeService {

    ModeRepositoryImpl modeRepository;

    public ModeService(ModeRepositoryImpl modeRepository) {
        this.modeRepository = modeRepository;
    }

    public List<ModeDTO> getAll() {
        return modeRepository.findAll().stream().map(s -> new ModeDTO(s.getId().toString(), s.getName())).collect(Collectors.toList());
    }

    public Mode get(String id) throws ValidationException {
        Optional<Mode> mode = modeRepository.findById(UUID.fromString(id));

        if (mode.isEmpty()) {
            throw new ValidationException("Mode with id" + id + DOES_NOT_EXIST);
        }
        return mode.get();
    }

    public ModeDTO getDTO(String id) {
        Mode mode = modeRepository.findById(UUID.fromString(id)).orElseThrow(
            NoSuchElementException::new);

        return new ModeDTO(mode.getId().toString(), mode.getName());
    }

    public String create(NewModeDTO newModeDTO) {
        Mode mode = new Mode();
        mode.setName(newModeDTO.getName());

        return modeRepository.save(mode).getId().toString();
    }

    public String update(String id, NewModeDTO newModeDTO) throws ValidationException {
        Optional<Mode> mode = modeRepository.findById(UUID.fromString(id));

        if (mode.isEmpty()) {
            throw new ValidationException("No mode with id: " + id);
        }
        Mode m = mode.get();
        m.setName(newModeDTO.getName());

        return modeRepository.save(m).getId().toString();
    }

    public void delete(String id) {
        modeRepository.deleteById(UUID.fromString(id));
    }

    public Set<SongDTO> getSongs(String id) {
        try {
            return this.get(id).getSongs().stream().map(SongMapper::toSongDTO).collect(Collectors.toSet());
        } catch (ValidationException e) {
            return Set.of();
        }
    }
}
