package songlist.model.song.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NewSongDTO {

    @NotNull
    @NotBlank
    private String name;
    private String rhythmId;
    private String danceId;
    private String modeId;
    private String comments;

}
