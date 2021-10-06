package songlist.model.song.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewSongDTO {

    @NotNull
    @NotBlank
    private String name;

    private String rhythmId;

    private String danceId;

    private String modeId;

    @Size(max = 150)
    private String comments;

}
