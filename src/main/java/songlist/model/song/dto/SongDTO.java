package songlist.model.song.dto;

import lombok.Data;

@Data
public class SongDTO {

    private String id;
    private String name;
    private String rhythmName;
    private String danceName;
    private String modeName;
    private String comments;
}
