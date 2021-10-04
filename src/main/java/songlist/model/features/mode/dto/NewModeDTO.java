package songlist.model.features.mode.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NewModeDTO {

    @NotNull
    @NotBlank
    private String name;
}
