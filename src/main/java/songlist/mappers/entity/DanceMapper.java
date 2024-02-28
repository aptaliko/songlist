package songlist.mappers.entity;

import java.util.List;
import org.mapstruct.Mapper;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.model.features.dance.Dance;

@Mapper
public interface DanceMapper {

  DanceDTO entityToDto(Dance dance);

  List<DanceDTO> entitiesToDtos(List<Dance> dances);

}
