package songlist.model.features.rhythm.dto;

import lombok.Data;

@Data
public class RhythmDTO {

    private String id;

    private String name;

    private String meter;

    public RhythmDTO(String id, String name, String meter) {
        this.id = id;
        this.name = name;
        this.meter = meter;
    }
}
