package songlist.model.features.mode.dto;

import lombok.Data;

@Data
public class ModeDTO {

    private String id;

    private String name;

    public ModeDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
