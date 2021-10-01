package songlist.model.song.dto;

import lombok.Data;

@Data
public class NewSongDTO {

    private String name;
    
    private String rhythmId;

    private String comments;

}
