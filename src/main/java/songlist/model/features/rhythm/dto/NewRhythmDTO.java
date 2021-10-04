package songlist.model.features.rhythm.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewRhythmDTO {

    @NotNull
    @NotBlank
    private String name;

    private String meter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }
}
