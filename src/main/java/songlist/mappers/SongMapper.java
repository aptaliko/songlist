package songlist.mappers;

import songlist.model.song.Song;
import songlist.model.song.dto.SongDTO;

public class SongMapper {

    private SongMapper(){}

    public static SongDTO toSongDTO (Song song) {
        SongDTO dto = new SongDTO();
        dto.setId(song.getId().toString());
        dto.setName(song.getName());
        dto.setComments(song.getComments());
        if (song.getRhythm() != null) {
            dto.setRhythmName(song.getRhythm().getName());
        }
        if (song.getDance() != null) {
            dto.setDanceName(song.getDance().getName());
        }
        if (song.getMode() != null) {
            dto.setModeName(song.getMode().getName());
        }

        return dto;
    }
}
