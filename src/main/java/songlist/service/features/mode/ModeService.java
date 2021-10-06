package songlist.service.features.mode;

import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.model.features.mode.Mode;
import songlist.model.features.mode.dto.ModeDTO;
import songlist.model.features.mode.dto.NewModeDTO;
import songlist.repository.features.mode.ModeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModeService {

    ModeRepository modeRepository;

    public ModeService(ModeRepository modeRepository) {
        this.modeRepository = modeRepository;
    }

    public List<ModeDTO> getAll() {
        return modeRepository.findAll().stream().map(s -> new ModeDTO(s.getId().toString(), s.getName())).collect(Collectors.toList());
    }

    public Optional<Mode> get(String id) {
        return modeRepository.findById(UUID.fromString(id));
    }

    public ModeDTO getDTO(String id) {
        Mode mode = modeRepository.getOne(UUID.fromString(id));

        return new ModeDTO(mode.getId().toString(), mode.getName());
    }

    public Optional<UUID> create(NewModeDTO newModeDTO) {
        Mode mode = new Mode();
        mode.setName(newModeDTO.getName());

        return Optional.of(modeRepository.save(mode).getId());
    }

    public void delete(String id) {
        modeRepository.deleteById(UUID.fromString(id));
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
}
