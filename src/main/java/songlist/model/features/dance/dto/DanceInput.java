package songlist.model.features.dance.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DanceInput {

    @NotNull
    @NotBlank
    private String name;
}
