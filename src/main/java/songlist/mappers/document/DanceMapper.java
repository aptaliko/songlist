package songlist.mappers.document;

import java.util.List;
import org.mapstruct.Mapper;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.modelnosql.features.dance.Dance;

@Mapper
public interface DanceMapper {

  DanceDTO documentToDto(Dance dance);

  List<DanceDTO> documentsToDtos(List<Dance> dances);

}
