package songlist.model.features.dance.dto;

import lombok.Data;

@Data
public class DanceDTO {

    private String id;

    private String name;

    public DanceDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
