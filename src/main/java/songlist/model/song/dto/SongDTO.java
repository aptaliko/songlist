package songlist.model.song.dto;

import lombok.Data;

@Data
public class SongDTO {

    private String id;
    private String name;
    private String rhythmName;
    private String comments;

    public SongDTO(String id, String name, String rhythmName, String comments) {
        this.id = id;
        this.name = name;
        this.rhythmName = rhythmName;
        this.comments = comments;
    }
}
